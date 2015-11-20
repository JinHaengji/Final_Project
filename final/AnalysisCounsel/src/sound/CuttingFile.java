package sound;

import java.io.File;
import java.io.IOException;
import javaFlacEncoder.FLACFileWriter;

import javax.sound.sampled.AudioFileFormat;
import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.UnsupportedAudioFileException;

//1��. wav ������ 10�� ������ wav���Ϸ� split
//�̰� main�Լ���? 
public class CuttingFile 
{
	public static void main(String[] args) throws UnsupportedAudioFileException, IOException 
	{
		int mil = 0;
		
		String type_w = ".wav";
		String type_f = ".flac";
		String path = "D://����//file//wav//test6_1114";
		String path_w = "D://����//file//wav//test6_1114" + type_w;
		String path_f = "D://����//file//flac//test6_1114";
	
		//test6_1114.wav : 28�� 
		int totaltime = 0;
		totaltime = (int) totalLength(path_w); //�����ϰ� +1�� �ұ�?
		//System.out.println(totaltime);
		
		//totaltime�� �� ���ڸ����� �޾ƿ;���.		
		//�׳� 10�ʸ��� ��� ���ư����ִ����� ���� �Ǵϱ�
		mil = (totaltime / 10) +1;
		//System.out.println(mil);
		
		//10�ʸ��� �ڸ���
		for(int i=0; i < mil; i++)
		{
			copyAudio(path_w, path + "_s" + (i+1) + type_w, i*10, 10); //��µǴ� ������ �������ϸ� ���� 1,2,3... ���ʴ�� ����
		}
		
		//2. ���� wav���� flac���� ��ȯ
		for(int i = 0; i < mil; i++)
		{
			File inputFile = new File(path + "_s" + (i+1) + type_w);
			AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(inputFile);
			AudioSystem.write(audioInputStream, FLACFileWriter.FLAC, new File(path_f + "_s" + (i+1) + type_f)); 
		}
		
		//3. ���� flac���Ͽ� api ����
		
	}
	
	public static double totalLength(String path_w) throws UnsupportedAudioFileException, IOException
	{
		File file = new File(path_w);
		AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(file);
		AudioFormat format = audioInputStream.getFormat();
		long frames = audioInputStream.getFrameLength();
		double durationInSeconds = (frames+0.0) / format.getFrameRate();  
		
		return durationInSeconds;
		//System.out.println(durationInSeconds);
	}
	
	public static void copyAudio(String sourceFileName, String destinationFileName, int startSecond, int secondsToCopy)
	{
		AudioInputStream inputStream = null;
		AudioInputStream shortenedStream = null;
		try
		{
		  File file = new File(sourceFileName);
		  AudioFileFormat fileFormat = AudioSystem.getAudioFileFormat(file);
		  AudioFormat format = fileFormat.getFormat();
		    inputStream = AudioSystem.getAudioInputStream(file);
		  int bytesPerSecond = format.getFrameSize() * (int)format.getFrameRate();
		  inputStream.skip(startSecond * bytesPerSecond);
		  long framesOfAudioToCopy = secondsToCopy * (int)format.getFrameRate();
		  shortenedStream = new AudioInputStream(inputStream, format, framesOfAudioToCopy);
		  File destinationFile = new File(destinationFileName);
		  AudioSystem.write(shortenedStream, fileFormat.getType(), destinationFile);
		} 
		catch (Exception e)
		{
		  System.out.println(e);
		} 
		finally
		{
		  if (inputStream != null) try { inputStream.close(); } catch (Exception e) { System.out.println(e); }
		  if (shortenedStream != null) try { shortenedStream.close(); } catch (Exception e) { System.out.println(e); }
		}
	}

}