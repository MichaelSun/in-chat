package org.xmpp.client.util;

import org.jivesoftware.smack.ConnectionConfiguration;
import org.jivesoftware.smack.ConnectionListener;
import org.jivesoftware.smack.PacketListener;
import org.jivesoftware.smack.XMPPConnection;
import org.jivesoftware.smack.XMPPException;
import org.jivesoftware.smack.filter.OrFilter;



public class XmppTool {

	private static XMPPConnection con = null;
	
	private static void openConnection() {
		try {
			//url���˿ڣ�Ҳ�����������ӵķ��������֣���ַ���˿ڣ��û���
			ConnectionConfiguration connConfig = new ConnectionConfiguration("192.168.1.137", 5222);

			con = new XMPPConnection(connConfig);
			con.connect();
			con.addConnectionListener(new ConnectionListener() {
	            @Override
				public void connectionClosed() {
	                
	            	System.out.println("�ر�����");
	            }

	            @Override
				public void connectionClosedOnError(Exception e) {
	            	System.out.println("�ر������쳣");
	            }

	            @Override
				public void reconnectingIn(int seconds) {
	            	System.out.println("��������"+seconds);
	            }

	            @Override
				public void reconnectionFailed(Exception e) {
	            	System.out.println("��������ʧ��");
	            }

	            @Override
				public void reconnectionSuccessful() {
	            	System.out.println("�������ӳɹ�");
	            }
	        });
			

			/*//��Ϣ����

			PacketFilter rosterPF = new PacketTypeFilter(RosterPacket.class);
	        PacketFilter IQPF = new PacketTypeFilter(IQ.class);
	        PacketFilter MSGPF = new PacketTypeFilter(Message.class);
	        PacketFilter PresencePF = new PacketTypeFilter(Presence.class);
	        PacketFilter AMPF = new PacketTypeFilter(AuthMechanism.class);
	        PacketFilter REPF = new PacketTypeFilter(Response.class);
	        
	        OrFilter allPF = new OrFilter(rosterPF, IQPF);
	        allPF.addFilter(MSGPF);
	        allPF.addFilter(PresencePF);
	        allPF.addFilter(AMPF);
	        allPF.addFilter(REPF);
	        PacketListener myListener = new PacketListener() {
	            public void processPacket(Packet pk) {
	            	if(pk instanceof Message){
	            		Message msg=(Message) pk;
	            		System.out.println("receive message : " +msg.getFrom()+msg.getBody());

	            		//���ܵ���Ϣ��洢�����ݿ⣬Ȼ��ˢ�½��档����δ��ȡ����������
	            		//����
	            		
	            		
	            	}
	            }
	        };
	        con.addPacketListener(myListener, allPF);*/

			
			
		}
		catch (XMPPException xe) 
		{
			xe.printStackTrace();
		}
	}

	public static XMPPConnection getConnection() {
		if (con == null) {
			openConnection();
		}
		return con;
	}

	public static void closeConnection() {
		con.disconnect();
		con = null;
	}

	public static void addPacketListener(PacketListener myListener,
			OrFilter allPF) {
		con.addPacketListener(myListener, allPF);
	}
}
