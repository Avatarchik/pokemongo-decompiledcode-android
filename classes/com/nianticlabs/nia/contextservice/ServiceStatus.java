package com.nianticlabs.nia.contextservice;

public enum ServiceStatus
{
  static
  {
    STOPPED = new ServiceStatus("STOPPED", 1);
    INITIALIZED = new ServiceStatus("INITIALIZED", 2);
    RUNNING = new ServiceStatus("RUNNING", 3);
    FAILED = new ServiceStatus("FAILED", 4);
    PERMISSION_DENIED = new ServiceStatus("PERMISSION_DENIED", 5);
    ServiceStatus[] arrayOfServiceStatus = new ServiceStatus[6];
    arrayOfServiceStatus[0] = UNDEFINED;
    arrayOfServiceStatus[1] = STOPPED;
    arrayOfServiceStatus[2] = INITIALIZED;
    arrayOfServiceStatus[3] = RUNNING;
    arrayOfServiceStatus[4] = FAILED;
    arrayOfServiceStatus[5] = PERMISSION_DENIED;
    $VALUES = arrayOfServiceStatus;
  }
  
  private ServiceStatus() {}
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/com/nianticlabs/nia/contextservice/ServiceStatus.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */