package speech_api;

import java.io.File;
import java.io.IOException;
import javaFlacEncoder.FLACFileWriter;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.UnsupportedAudioFileException;

public class Main
{
	public static void main(String[] args) throws IOException, UnsupportedAudioFileException
	{
		//1. wav -> flac 파일 변환 
		Convert convert = new Convert();
		
		//2. flac 파일 분할
		
		
		//3. api 적용     
	    STT stt = new STT();
	}
}
