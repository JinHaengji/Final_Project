package api;

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
			String path_f = "";
			
			//url 객체 선언
			URL url;
			String key = "AIzaSyDXvTfW775bucO4mcVMB03m3xCB7K4ayfc";
			//String key = "AIzaSyDHX2gteZVJqAD7y5A5S85fou85rwFeO_M";
			
		      //HTTP POST로 요청
		      //두가지 data형식을 인식
		      //HOST 주소:https://www.google.com/speech-api/v2/recognize 결과값:JSON 한국어로 번역:'ko-KR'
		      String urlString = "https://www.google.com/speech-api/v2/recognize?output=json&lang=ko-KR&key=" + key;
		          try 
		          {
		             //URL클래스의 생성자로 주소를 넘겨준다.
		             url = new URL(urlString);
		             
		             //Http를 이용하여 해당 웹사이트에 접속해서 데이터를 주고 받을때 사용합니다.
		             HttpURLConnection connection = (HttpURLConnection) url.openConnection();
		             
		             //StringBuilder 객체 정의, 문자열을 담는 역할이지만 문자열을 수정가능
		             StringBuilder stringBuilder;
		   
		             //cache를 사용할 것 인지 (boolean)
		             //true의 경우 , 프로토콜은 가능한 때에 캐쉬 (cache) 를 사용할 수 있다.
		             connection.setDefaultUseCaches(true);
		             
		             //timeout은 어떠한 서버로 연결을 할 때 시도하여 성공하지 못하였을 때의 시간설정 (20초)
		             connection.setConnectTimeout(60000);
		             
		             //inputStream으로 읽어오면서 delay될 때의 시간설정(1분)
		             connection.setReadTimeout(120000);
		             
		             //InputStream으로 응답 헤더와 메시지를 읽어들이겠다는 옵션을 정의(서버에서 읽기모드 지정)
		             connection.setDoInput(true);
		             
		            //OutputStream으로 POST 데이터를 넘겨주겠다는 옵션을 정의(서버에서 쓰기모드 지정)   
		             connection.setDoOutput(true);
		             
		             //true의 경우 , 프로토콜은 자동적으로 리다이렉트 (redirect)에 따름 
		             connection.setInstanceFollowRedirects(true);
		             
		             //POST방식으로 요청한다.
		             connection.setRequestMethod("POST");
		             
		            //요청 헤더를 정의한다.
		             connection.setRequestProperty("Content-Type", "audio/x-flac;rate=44100");
		   
		             /*Write the audio on bufferI/O*/
		             //지정된 경로의 파일을 선언
		             File file = new File("D://졸작//file//flac//temp5_1114.flac");
		             //File file = new File("D://test1.flac");
		             
		             //파일의 길이만큼 크기생성
		             byte[] buffer = new byte[(int) file.length()];
		             
		             //inputstream 객체 생성
		             FileInputStream fis = new FileInputStream(file);
		             
		             //inputstream으로 파일을 읽음
		             fis.read(buffer);
		             
		             //닫기
		             fis.close();
		             
		             //outputstream생성
		             OutputStream os = connection.getOutputStream();
		             
		             //파일 읽기
		             os.write(buffer);
		             
		             //닫기
		             os.close();
		   
		             //google server와 연결
		             connection.connect();
		             System.out.println("connect to google server!");
		             
		             //응답메세지
		             connection.getResponseMessage();
		             System.out.println("receive the reponse!");
		             
		             InputStreamReader inputStreamReader = new InputStreamReader(connection.getInputStream(), "UTF-8");
		             BufferedReader br = new BufferedReader(inputStreamReader);
		   
		             stringBuilder = new StringBuilder();
		             
		             //System.out.println(stringBuilder);
		             String line = null;
		             
		             //System.out.println("1");
		             //Split split = new Split();
		             //파일에 아무값이 없을 때까지 반복
		               while ((line = br.readLine()) != null)
		               {
		            	 
		                 stringBuilder.append(line + "\n");
		                 //System.out.println("2");
		                 //stringBuilder가 몇줄? 메모리? 가 넘어가면 끊어주기 
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
