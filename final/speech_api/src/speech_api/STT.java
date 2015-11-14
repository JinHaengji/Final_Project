package speech_api;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class STT
{
	public STT()
	{
		//url ��ü ����
		URL url;
		String key = "AIzaSyDXvTfW775bucO4mcVMB03m3xCB7K4ayfc";
		//String key = "AIzaSyDHX2gteZVJqAD7y5A5S85fou85rwFeO_M";
		
	      //HTTP POST�� ��û
	      //�ΰ��� data������ �ν�
	      //HOST �ּ�:https://www.google.com/speech-api/v2/recognize �����:JSON �ѱ���� ����:'ko-KR'
	      String urlString = "https://www.google.com/speech-api/v2/recognize?output=json&lang=ko-KR&key=" + key;
	          try 
	          {
	             //URLŬ������ �����ڷ� �ּҸ� �Ѱ��ش�.
	             url = new URL(urlString);
	             
	             //Http�� �̿��Ͽ� �ش� ������Ʈ�� �����ؼ� �����͸� �ְ� ������ ����մϴ�.
	             HttpURLConnection connection = (HttpURLConnection) url.openConnection();
	             
	             //StringBuilder ��ü ����, ���ڿ��� ��� ���������� ���ڿ��� ��������
	             StringBuilder stringBuilder;
	   
	             //cache�� ����� �� ���� (boolean)
	             //true�� ��� , ���������� ������ ���� ĳ�� (cache) �� ����� �� �ִ�.
	             connection.setDefaultUseCaches(true);
	             
	             //timeout�� ��� ������ ������ �� �� �õ��Ͽ� �������� ���Ͽ��� ���� �ð����� (20��)
	             connection.setConnectTimeout(60000);
	             
	             //inputStream���� �о���鼭 delay�� ���� �ð�����(1��)
	             connection.setReadTimeout(120000);
	             
	             //InputStream���� ���� ����� �޽����� �о���̰ڴٴ� �ɼ��� ����(�������� �б��� ����)
	             connection.setDoInput(true);
	             
	            //OutputStream���� POST �����͸� �Ѱ��ְڴٴ� �ɼ��� ����(�������� ������ ����)   
	             connection.setDoOutput(true);
	             
	             //true�� ��� , ���������� �ڵ������� �����̷�Ʈ (redirect)�� ���� 
	             connection.setInstanceFollowRedirects(true);
	             
	             //POST������� ��û�Ѵ�.
	             connection.setRequestMethod("POST");
	             
	            //��û ����� �����Ѵ�.
	             connection.setRequestProperty("Content-Type", "audio/x-flac;rate=44100");
	   
	             /*Write the audio on bufferI/O*/
	             //������ ����� ������ ����
	             File file = new File("D://����//file//flac//temp4.flac");
	             //File file = new File("D://test1.flac");
	             
	             //������ ���̸�ŭ ũ�����
	             byte[] buffer = new byte[(int) file.length()];
	             
	             //inputstream ��ü ����
	             FileInputStream fis = new FileInputStream(file);
	             
	             //inputstream���� ������ ����
	             fis.read(buffer);
	             
	             //�ݱ�
	             fis.close();
	             
	             //outputstream����
	             OutputStream os = connection.getOutputStream();
	             
	             //���� �б�
	             os.write(buffer);
	             
	             //�ݱ�
	             os.close();
	   
	             //google server�� ����
	             connection.connect();
	             System.out.println("connect to google server!");
	             
	             //����޼���
	             connection.getResponseMessage();
	             System.out.println("receive the reponse!");
	             
	             InputStreamReader inputStreamReader = new InputStreamReader(connection.getInputStream(), "UTF-8");
	             BufferedReader br = new BufferedReader(inputStreamReader);
	   
	             stringBuilder = new StringBuilder();
	             
	             //System.out.println(stringBuilder);
	             String line = null;
	             
	             //System.out.println("1");
	             //Split split = new Split();
	             //���Ͽ� �ƹ����� ���� ������ �ݺ�
	               while ((line = br.readLine()) != null)
	               {
	            	 
	                 stringBuilder.append(line + "\n");
	                 //System.out.println("2");
	                 //stringBuilder�� ����? �޸�? �� �Ѿ�� �����ֱ� 
	                 //if(line > )
	                 //Split.lengthLimit(line, 10, "end");
	               }
	               
	               System.out.println(stringBuilder);
	               //System.out.println("3");
	              // JSONObject jsonResponse = new JSONObject(stringBuilder.toString());
	      }
	        catch (MalformedURLException e)
	          {
	          // TODO Auto-generated catch block
	          e.printStackTrace();
	      } catch (IOException e) {
	          // TODO Auto-generated catch block
	          e.printStackTrace();
	      }
	}
}