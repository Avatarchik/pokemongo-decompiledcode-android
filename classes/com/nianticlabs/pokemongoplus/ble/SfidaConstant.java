package com.nianticlabs.pokemongoplus.ble;

import java.util.UUID;

public class SfidaConstant
{
  public static final String PERIPHERAL_NAME = "Pokemon GO Plus";
  public static final String SFIDA_RESPONSE_CERTIFICATION_CHALLENGE_1 = "4010";
  public static final String SFIDA_RESPONSE_CERTIFICATION_CHALLENGE_2 = "5000";
  public static final String SFIDA_RESPONSE_CERTIFICATION_COMPLETE = "4020";
  public static final String SFIDA_RESPONSE_CERTIFICATION_NOTIFY = "3000";
  public static final UUID UUID_BATTERY_LEVEL_CHAR = UUID.fromString("00002A19-0000-1000-8000-00805f9b34fb");
  public static final UUID UUID_BATTERY_SERVICE;
  public static final UUID UUID_BUTTON_NOTIF_CHAR;
  public static final UUID UUID_CENTRAL_TO_SFIDA_CHAR;
  public static final UUID UUID_CERTIFICATE_SERVICE;
  public static final UUID UUID_CLIENT_CHARACTERISTIC_CONFIG = UUID.fromString("00002902-0000-1000-8000-00805f9b34fb");
  public static final UUID UUID_DEVICE_CONTROL_SERVICE;
  public static final UUID UUID_FW_UPDATE_REQUEST_CHAR;
  public static final UUID UUID_FW_UPDATE_SERVICE = UUID.fromString("0000fef5-0000-1000-8000-00805f9b34fb");
  public static final UUID UUID_FW_VERSION_CHAR;
  public static final UUID UUID_LED_VIBRATE_CTRL_CHAR;
  public static final UUID UUID_SFIDA_COMMANDS_CHAR;
  public static final UUID UUID_SFIDA_TO_CENTRAL_CHAR;
  
  static
  {
    UUID_DEVICE_CONTROL_SERVICE = UUID.fromString("21c50462-67cb-63a3-5c4c-82b5b9939aeb");
    UUID_LED_VIBRATE_CTRL_CHAR = UUID.fromString("21c50462-67cb-63a3-5c4c-82b5b9939aec");
    UUID_BUTTON_NOTIF_CHAR = UUID.fromString("21c50462-67cb-63a3-5c4c-82b5b9939aed");
    UUID_FW_UPDATE_REQUEST_CHAR = UUID.fromString("21c50462-67cb-63a3-5c4c-82b5b9939aef");
    UUID_FW_VERSION_CHAR = UUID.fromString("21c50462-67cb-63a3-5c4c-82b5b9939af0");
    UUID_CERTIFICATE_SERVICE = UUID.fromString("bbe87709-5b89-4433-ab7f-8b8eef0d8e37");
    UUID_CENTRAL_TO_SFIDA_CHAR = UUID.fromString("bbe87709-5b89-4433-ab7f-8b8eef0d8e38");
    UUID_SFIDA_COMMANDS_CHAR = UUID.fromString("bbe87709-5b89-4433-ab7f-8b8eef0d8e39");
    UUID_SFIDA_TO_CENTRAL_CHAR = UUID.fromString("bbe87709-5b89-4433-ab7f-8b8eef0d8e3a");
    UUID_BATTERY_SERVICE = UUID.fromString("0000180F-0000-1000-8000-00805f9b34fb");
  }
  
  public static enum CentralState
  {
    private final int id;
    
    static
    {
      Resetting = new CentralState("Resetting", 1, 1);
      Unsupported = new CentralState("Unsupported", 2, 2);
      Unauthorized = new CentralState("Unauthorized", 3, 3);
      PoweredOff = new CentralState("PoweredOff", 4, 4);
      PoweredOn = new CentralState("PoweredOn", 5, 5);
      CentralState[] arrayOfCentralState = new CentralState[6];
      arrayOfCentralState[0] = Unknown;
      arrayOfCentralState[1] = Resetting;
      arrayOfCentralState[2] = Unsupported;
      arrayOfCentralState[3] = Unauthorized;
      arrayOfCentralState[4] = PoweredOff;
      arrayOfCentralState[5] = PoweredOn;
      $VALUES = arrayOfCentralState;
    }
    
    private CentralState(int paramInt)
    {
      this.id = paramInt;
    }
    
    public int getInt()
    {
      return this.id;
    }
  }
  
  public static enum PeripheralState
  {
    private final int id;
    
    static
    {
      Connecting = new PeripheralState("Connecting", 1, 1);
      Connected = new PeripheralState("Connected", 2, 2);
      PeripheralState[] arrayOfPeripheralState = new PeripheralState[3];
      arrayOfPeripheralState[0] = Disconnected;
      arrayOfPeripheralState[1] = Connecting;
      arrayOfPeripheralState[2] = Connected;
      $VALUES = arrayOfPeripheralState;
    }
    
    private PeripheralState(int paramInt)
    {
      this.id = paramInt;
    }
    
    public int getInt()
    {
      return this.id;
    }
  }
  
  public static enum BluetoothError
  {
    private final int id;
    
    static
    {
      InvalidParameters = new BluetoothError("InvalidParameters", 1, 2);
      InvalidHandle = new BluetoothError("InvalidHandle", 2, 3);
      NotConnected = new BluetoothError("NotConnected", 3, 4);
      OutOfSpace = new BluetoothError("OutOfSpace", 4, 5);
      OperationCancelled = new BluetoothError("OperationCancelled", 5, 6);
      ConnectionTimeout = new BluetoothError("ConnectionTimeout", 6, 7);
      PeripheralDisconnected = new BluetoothError("PeripheralDisconnected", 7, 8);
      UUIDNotAllowed = new BluetoothError("UUIDNotAllowed", 8, 9);
      AlreadyAdvertising = new BluetoothError("AlreadyAdvertising", 9, 10);
      ConnectionFailed = new BluetoothError("ConnectionFailed", 10, 11);
      BluetoothError[] arrayOfBluetoothError = new BluetoothError[11];
      arrayOfBluetoothError[0] = Unknown;
      arrayOfBluetoothError[1] = InvalidParameters;
      arrayOfBluetoothError[2] = InvalidHandle;
      arrayOfBluetoothError[3] = NotConnected;
      arrayOfBluetoothError[4] = OutOfSpace;
      arrayOfBluetoothError[5] = OperationCancelled;
      arrayOfBluetoothError[6] = ConnectionTimeout;
      arrayOfBluetoothError[7] = PeripheralDisconnected;
      arrayOfBluetoothError[8] = UUIDNotAllowed;
      arrayOfBluetoothError[9] = AlreadyAdvertising;
      arrayOfBluetoothError[10] = ConnectionFailed;
      $VALUES = arrayOfBluetoothError;
    }
    
    private BluetoothError(int paramInt)
    {
      this.id = paramInt;
    }
    
    public int getInt()
    {
      return this.id;
    }
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/com/nianticlabs/pokemongoplus/ble/SfidaConstant.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */