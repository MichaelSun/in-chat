package pro.chinasoft.activity;

import java.util.Date;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.jivesoftware.smack.Chat;
import org.jivesoftware.smack.ChatManager;
import org.jivesoftware.smack.XMPPException;
import org.xmpp.client.util.InMessageStore;
import org.xmpp.client.util.XmppTool;

import pro.chinasoft.component.InMessageArrayAdapter;
import pro.chinasoft.model.InMessage;
import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;

public class InChatActivity extends Activity {

	private ListView listView;


	private ArrayAdapter<InMessage> messages;

	private ChatManager cm;
	private Chat chat;
	private String friendId;
	private String userId;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.in_chat_activity);

		listView = (ListView) this.findViewById(R.id.chatHistory);
		String defaultValue = getResources().getString(
				R.string.username_store_key);
		friendId = getIntent().getStringExtra(defaultValue);
		SharedPreferences sharedPref = this.getSharedPreferences(
				getString(R.string.in_chat_store), Context.MODE_PRIVATE);
		userId = sharedPref.getString(getString(R.string.username_store_key),
				"");
		// �����¼��listview ������ԭ
		messages = new InMessageArrayAdapter(this.getApplicationContext(),
				R.layout.in_message_list_item);
		List<InMessage> msgs = InMessageStore.getMessages(userId, friendId, 0,
				5, this);

		for (int i=msgs.size();i>0;i--) {
			messages.add(msgs.get(i-1));
			System.out.println(i);
		}
		listView.setAdapter(messages);
		cm = XmppTool.getConnection().getChatManager();
		chat = cm.createChat(friendId, null);

		// ע��㲥������������������Ϣ
		
		IntentFilter intentFilter = new IntentFilter(
				"pro.chinasoft.activity.InChatActivity");
		registerReceiver(mReceiver, intentFilter);

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.in_chat, menu);
		return true;
	}

	
	

	@Override
	public void onBackPressed() {
		unregisterReceiver(mReceiver);
		InMessageStore.close();
		System.out.println("ע������ �ر����ݿ�	");
		this.finish();
	}

	public void sendMessage(View view) {
		EditText text = (EditText) this.findViewById(R.id.content);
		String message = text.getText().toString();
		// ˢ������
		refresh(message);
		// ���浽sqlite
		saveOrUpdate(userId, friendId, message);
		try {
			chat.sendMessage(message);
		} catch (XMPPException e) {
			System.out.println(e.getMessage());
		}


		// ��������Ϣ�����ԭ��������
		text.setText("");
	}

	private void saveOrUpdate(String userId, String firendId, String content) {
		Map<String, String> map = new HashMap<String, String>();
		map.put("content", content);
		map.put("userId", userId);
		map.put("friendId", friendId);
		InMessageStore.add("InMessage", map, this);
	}

	private void refresh(String content) {
		InMessage msg = new InMessage();
		msg.setContent(content);
		msg.setReviceDate(new Date());
		messages.add(msg);
		messages.notifyDataSetChanged();
	}
	BroadcastReceiver mReceiver = new BroadcastReceiver() {
		@Override
		public void onReceive(Context context, Intent intent) {
			// ˢ����Activity����
			String friendId = intent.getStringExtra("friendId");
			if (friendId.equals(friendId)) {
				String content = intent.getStringExtra("content");
				saveOrUpdate(userId, friendId, content);
				refresh(content);
				
			}
			System.out.println("//"+friendId);
		}
	};
}
