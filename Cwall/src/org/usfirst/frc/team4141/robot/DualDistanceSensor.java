package org.usfirst.frc.team4141.robot;

import java.nio.ByteBuffer;
import java.util.Hashtable;

import edu.wpi.first.wpilibj.I2C;

public class DualDistanceSensor {
   private static final char LED_COMMAND = 'L';
   private static final char LED_ON = 'H';
   private static final char LED_OFF = 'L';
   private static final byte DATA_ADDRESS = 0x02;
   private static final byte MODE_ADDRESS = 0x06;
   private static final short DATA_SIZE = 4;  //in bytes
   private static final short MODE_COMMAND_SIZE = 7;
   MD_IMU_Component[] distances = new MD_IMU_Component[2];

   static byte [] charToByteArray( char c )
   {
      byte [] twoBytes = { (byte)(c >> 8 & 0xff), (byte)(c & 0xff) };
      return twoBytes;
   }
   private I2C i2cDevice;

   public DualDistanceSensor(int address){
	   i2cDevice=new I2C(I2C.Port.kOnboard, address);
	   int i=0;
	   distances[i++]=new MD_IMU_Component("distance0", 0);
	   distances[i++]=new MD_IMU_Component("distance1", 0);
   }


	public MD_IMU_Component[] read(Hashtable<String, Telemetry> sensors){
		update();
		for(int i=0;i<distances.length;i++){
			if(!sensors.containsKey(distances[i].getName())){
				sensors.put(distances[i].getName(), distances[i]);
			}
		}
		return distances;
	}   
   private void update() {
	   ByteBuffer data = ByteBuffer.allocateDirect(DATA_SIZE);//size of 2 char (e.g. 2 byte unsigned ints
	   
		if(i2cDevice.read(DATA_ADDRESS, DATA_SIZE, data)){
			for(int i=0;i<DATA_SIZE/2;i++){
				distances[i].setValue(data.getChar()); 
//				if(i>0) System.out.print("\t");
//				System.out.printf("%d", (int)(distances[i].getValue()));
			}
//			System.out.println();

		}
	}

public void setStatusLed(boolean state){
	   sendCommand(LED_COMMAND,new char[]{(state?LED_ON:LED_OFF),0x00});
   }

   	private void sendCommand(char command, char[] commandData) {
   		switch(command){
		case LED_COMMAND:
			//this is a mode command
			//device currently supports a 3 unsigned 2byte int e.g. char structure 
			//in addition, mode is currently defined at register address 0x06
			//create the command byte[]
			ByteBuffer data = ByteBuffer.allocateDirect(MODE_COMMAND_SIZE);
			data.put(MODE_ADDRESS);
			data.put(charToByteArray(command));
			for(char c : commandData){
				data.put(charToByteArray(c));
			}
			i2cDevice.writeBulk(data,MODE_COMMAND_SIZE);
			break;
		}
	}
}
