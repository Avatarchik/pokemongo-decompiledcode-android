package com.google.android.gms.internal;

import java.io.IOException;
import java.util.Arrays;

public abstract interface zzsi
{
  public static final class zzb
    extends zzry<zzb>
  {
    public String version;
    public int zzbiJ;
    public String zzbiK;
    
    public zzb()
    {
      zzFT();
    }
    
    public boolean equals(Object paramObject)
    {
      boolean bool = false;
      if (paramObject == this) {
        bool = true;
      }
      for (;;)
      {
        return bool;
        if ((paramObject instanceof zzb))
        {
          zzb localzzb = (zzb)paramObject;
          if (this.zzbiJ == localzzb.zzbiJ)
          {
            if (this.zzbiK == null)
            {
              if (localzzb.zzbiK != null) {
                continue;
              }
              label48:
              if (this.version != null) {
                break label118;
              }
              if (localzzb.version != null) {
                continue;
              }
            }
            for (;;)
            {
              if ((this.zzbik == null) || (this.zzbik.isEmpty()))
              {
                if ((localzzb.zzbik != null) && (!localzzb.zzbik.isEmpty())) {
                  break;
                }
                bool = true;
                break;
                if (this.zzbiK.equals(localzzb.zzbiK)) {
                  break label48;
                }
                break;
                label118:
                if (!this.version.equals(localzzb.version)) {
                  break;
                }
              }
            }
            bool = this.zzbik.equals(localzzb.zzbik);
          }
        }
      }
    }
    
    public int hashCode()
    {
      int i = 0;
      int j = 31 * (31 * (527 + getClass().getName().hashCode()) + this.zzbiJ);
      int k;
      int n;
      label55:
      int i1;
      if (this.zzbiK == null)
      {
        k = 0;
        int m = 31 * (k + j);
        if (this.version != null) {
          break label98;
        }
        n = 0;
        i1 = 31 * (n + m);
        if ((this.zzbik != null) && (!this.zzbik.isEmpty())) {
          break label110;
        }
      }
      for (;;)
      {
        return i1 + i;
        k = this.zzbiK.hashCode();
        break;
        label98:
        n = this.version.hashCode();
        break label55;
        label110:
        i = this.zzbik.hashCode();
      }
    }
    
    protected int zzB()
    {
      int i = super.zzB();
      if (this.zzbiJ != 0) {
        i += zzrx.zzA(1, this.zzbiJ);
      }
      if (!this.zzbiK.equals("")) {
        i += zzrx.zzn(2, this.zzbiK);
      }
      if (!this.version.equals("")) {
        i += zzrx.zzn(3, this.version);
      }
      return i;
    }
    
    public zzb zzFT()
    {
      this.zzbiJ = 0;
      this.zzbiK = "";
      this.version = "";
      this.zzbik = null;
      this.zzbiv = -1;
      return this;
    }
    
    public zzb zzH(zzrw paramzzrw)
      throws IOException
    {
      for (;;)
      {
        int i = paramzzrw.zzFo();
        switch (i)
        {
        default: 
          if (zza(paramzzrw, i)) {}
          break;
        case 0: 
          return this;
        case 8: 
          int j = paramzzrw.zzFr();
          switch (j)
          {
          default: 
            break;
          case 0: 
          case 1: 
          case 2: 
          case 3: 
          case 4: 
          case 5: 
          case 6: 
          case 7: 
          case 8: 
          case 9: 
          case 10: 
          case 11: 
          case 12: 
          case 13: 
          case 14: 
          case 15: 
          case 16: 
          case 17: 
          case 18: 
          case 19: 
          case 20: 
          case 21: 
          case 22: 
          case 23: 
          case 24: 
          case 25: 
          case 26: 
            this.zzbiJ = j;
          }
          break;
        case 18: 
          this.zzbiK = paramzzrw.readString();
          break;
        case 26: 
          this.version = paramzzrw.readString();
        }
      }
    }
    
    public void zza(zzrx paramzzrx)
      throws IOException
    {
      if (this.zzbiJ != 0) {
        paramzzrx.zzy(1, this.zzbiJ);
      }
      if (!this.zzbiK.equals("")) {
        paramzzrx.zzb(2, this.zzbiK);
      }
      if (!this.version.equals("")) {
        paramzzrx.zzb(3, this.version);
      }
      super.zza(paramzzrx);
    }
  }
  
  public static final class zzc
    extends zzry<zzc>
  {
    public byte[] zzbiL;
    public byte[][] zzbiM;
    public boolean zzbiN;
    
    public zzc()
    {
      zzFU();
    }
    
    public boolean equals(Object paramObject)
    {
      boolean bool = false;
      if (paramObject == this) {
        bool = true;
      }
      for (;;)
      {
        return bool;
        if ((paramObject instanceof zzc))
        {
          zzc localzzc = (zzc)paramObject;
          if ((Arrays.equals(this.zzbiL, localzzc.zzbiL)) && (zzsc.zza(this.zzbiM, localzzc.zzbiM)) && (this.zzbiN == localzzc.zzbiN)) {
            if ((this.zzbik == null) || (this.zzbik.isEmpty()))
            {
              if ((localzzc.zzbik == null) || (localzzc.zzbik.isEmpty())) {
                bool = true;
              }
            }
            else {
              bool = this.zzbik.equals(localzzc.zzbik);
            }
          }
        }
      }
    }
    
    public int hashCode()
    {
      int i = 31 * (31 * (31 * (527 + getClass().getName().hashCode()) + Arrays.hashCode(this.zzbiL)) + zzsc.zza(this.zzbiM));
      int j;
      int k;
      if (this.zzbiN)
      {
        j = 1231;
        k = 31 * (j + i);
        if ((this.zzbik != null) && (!this.zzbik.isEmpty())) {
          break label90;
        }
      }
      label90:
      for (int m = 0;; m = this.zzbik.hashCode())
      {
        return m + k;
        j = 1237;
        break;
      }
    }
    
    protected int zzB()
    {
      int i = 0;
      int j = super.zzB();
      if (!Arrays.equals(this.zzbiL, zzsh.zzbiE)) {
        j += zzrx.zzb(1, this.zzbiL);
      }
      if ((this.zzbiM != null) && (this.zzbiM.length > 0))
      {
        int k = 0;
        int m = 0;
        while (i < this.zzbiM.length)
        {
          byte[] arrayOfByte = this.zzbiM[i];
          if (arrayOfByte != null)
          {
            m++;
            k += zzrx.zzE(arrayOfByte);
          }
          i++;
        }
        j = j + k + m * 1;
      }
      if (this.zzbiN) {
        j += zzrx.zzc(3, this.zzbiN);
      }
      return j;
    }
    
    public zzc zzFU()
    {
      this.zzbiL = zzsh.zzbiE;
      this.zzbiM = zzsh.zzbiD;
      this.zzbiN = false;
      this.zzbik = null;
      this.zzbiv = -1;
      return this;
    }
    
    public zzc zzI(zzrw paramzzrw)
      throws IOException
    {
      for (;;)
      {
        int i = paramzzrw.zzFo();
        switch (i)
        {
        default: 
          if (zza(paramzzrw, i)) {}
          break;
        case 0: 
          return this;
        case 10: 
          this.zzbiL = paramzzrw.readBytes();
          break;
        case 18: 
          int j = zzsh.zzc(paramzzrw, 18);
          if (this.zzbiM == null) {}
          byte[][] arrayOfByte;
          for (int k = 0;; k = this.zzbiM.length)
          {
            arrayOfByte = new byte[j + k][];
            if (k != 0) {
              System.arraycopy(this.zzbiM, 0, arrayOfByte, 0, k);
            }
            while (k < -1 + arrayOfByte.length)
            {
              arrayOfByte[k] = paramzzrw.readBytes();
              paramzzrw.zzFo();
              k++;
            }
          }
          arrayOfByte[k] = paramzzrw.readBytes();
          this.zzbiM = arrayOfByte;
          break;
        case 24: 
          this.zzbiN = paramzzrw.zzFs();
        }
      }
    }
    
    public void zza(zzrx paramzzrx)
      throws IOException
    {
      if (!Arrays.equals(this.zzbiL, zzsh.zzbiE)) {
        paramzzrx.zza(1, this.zzbiL);
      }
      if ((this.zzbiM != null) && (this.zzbiM.length > 0)) {
        for (int i = 0; i < this.zzbiM.length; i++)
        {
          byte[] arrayOfByte = this.zzbiM[i];
          if (arrayOfByte != null) {
            paramzzrx.zza(2, arrayOfByte);
          }
        }
      }
      if (this.zzbiN) {
        paramzzrx.zzb(3, this.zzbiN);
      }
      super.zza(paramzzrx);
    }
  }
  
  public static final class zzd
    extends zzry<zzd>
  {
    public String tag;
    public long zzbiO;
    public long zzbiP;
    public int zzbiQ;
    public int zzbiR;
    public boolean zzbiS;
    public zzsi.zze[] zzbiT;
    public zzsi.zzb zzbiU;
    public byte[] zzbiV;
    public byte[] zzbiW;
    public byte[] zzbiX;
    public zzsi.zza zzbiY;
    public String zzbiZ;
    public long zzbja;
    public zzsi.zzc zzbjb;
    public byte[] zzbjc;
    public int zzbjd;
    public int[] zzbje;
    
    public zzd()
    {
      zzFV();
    }
    
    public boolean equals(Object paramObject)
    {
      boolean bool = false;
      if (paramObject == this)
      {
        bool = true;
        break label218;
        break label192;
        break label122;
      }
      for (;;)
      {
        label9:
        return bool;
        if ((paramObject instanceof zzd))
        {
          zzd localzzd = (zzd)paramObject;
          if ((this.zzbiO == localzzd.zzbiO) && (this.zzbiP == localzzd.zzbiP))
          {
            if (this.tag == null)
            {
              if (localzzd.tag != null) {
                continue;
              }
              if ((this.zzbiQ != localzzd.zzbiQ) || (this.zzbiR != localzzd.zzbiR) || (this.zzbiS != localzzd.zzbiS) || (!zzsc.equals(this.zzbiT, localzzd.zzbiT))) {
                continue;
              }
              if (this.zzbiU != null) {
                break label313;
              }
              if (localzzd.zzbiU != null) {
                continue;
              }
              label122:
              if ((!Arrays.equals(this.zzbiV, localzzd.zzbiV)) || (!Arrays.equals(this.zzbiW, localzzd.zzbiW)) || (!Arrays.equals(this.zzbiX, localzzd.zzbiX))) {
                continue;
              }
              if (this.zzbiY != null) {
                break label330;
              }
              if (localzzd.zzbiY != null) {
                continue;
              }
            }
            for (;;)
            {
              if (this.zzbiZ == null)
              {
                if (localzzd.zzbiZ != null) {
                  break label9;
                }
                label192:
                if (this.zzbja != localzzd.zzbja) {
                  break label9;
                }
                if (this.zzbjb != null) {
                  break label364;
                }
                if (localzzd.zzbjb != null) {
                  break label9;
                }
                label218:
                if ((!Arrays.equals(this.zzbjc, localzzd.zzbjc)) || (this.zzbjd != localzzd.zzbjd) || (!zzsc.equals(this.zzbje, localzzd.zzbje))) {
                  break label9;
                }
                if ((this.zzbik != null) && (!this.zzbik.isEmpty())) {
                  break label381;
                }
                if ((localzzd.zzbik != null) && (!localzzd.zzbik.isEmpty())) {
                  break label9;
                }
                bool = true;
                break label9;
                if (this.tag.equals(localzzd.tag)) {
                  break;
                }
                break label9;
                label313:
                if (this.zzbiU.equals(localzzd.zzbiU)) {
                  break;
                }
                break label9;
                label330:
                if (!this.zzbiY.equals(localzzd.zzbiY)) {
                  break label9;
                }
              }
            }
            if (this.zzbiZ.equals(localzzd.zzbiZ)) {
              break;
            }
            continue;
            label364:
            if (this.zzbjb.equals(localzzd.zzbjb)) {
              break;
            }
            continue;
            label381:
            bool = this.zzbik.equals(localzzd.zzbik);
          }
        }
      }
    }
    
    public int hashCode()
    {
      int i = 0;
      int j = 31 * (31 * (31 * (527 + getClass().getName().hashCode()) + (int)(this.zzbiO ^ this.zzbiO >>> 32)) + (int)(this.zzbiP ^ this.zzbiP >>> 32));
      int k;
      int n;
      label99:
      int i2;
      label130:
      int i4;
      label183:
      int i6;
      label203:
      int i8;
      label240:
      int i9;
      if (this.tag == null)
      {
        k = 0;
        int m = 31 * (31 * (31 * (k + j) + this.zzbiQ) + this.zzbiR);
        if (!this.zzbiS) {
          break label313;
        }
        n = 1231;
        int i1 = 31 * (31 * (n + m) + zzsc.hashCode(this.zzbiT));
        if (this.zzbiU != null) {
          break label321;
        }
        i2 = 0;
        int i3 = 31 * (31 * (31 * (31 * (i2 + i1) + Arrays.hashCode(this.zzbiV)) + Arrays.hashCode(this.zzbiW)) + Arrays.hashCode(this.zzbiX));
        if (this.zzbiY != null) {
          break label333;
        }
        i4 = 0;
        int i5 = 31 * (i4 + i3);
        if (this.zzbiZ != null) {
          break label345;
        }
        i6 = 0;
        int i7 = 31 * (31 * (i6 + i5) + (int)(this.zzbja ^ this.zzbja >>> 32));
        if (this.zzbjb != null) {
          break label357;
        }
        i8 = 0;
        i9 = 31 * (31 * (31 * (31 * (i8 + i7) + Arrays.hashCode(this.zzbjc)) + this.zzbjd) + zzsc.hashCode(this.zzbje));
        if ((this.zzbik != null) && (!this.zzbik.isEmpty())) {
          break label369;
        }
      }
      for (;;)
      {
        return i9 + i;
        k = this.tag.hashCode();
        break;
        label313:
        n = 1237;
        break label99;
        label321:
        i2 = this.zzbiU.hashCode();
        break label130;
        label333:
        i4 = this.zzbiY.hashCode();
        break label183;
        label345:
        i6 = this.zzbiZ.hashCode();
        break label203;
        label357:
        i8 = this.zzbjb.hashCode();
        break label240;
        label369:
        i = this.zzbik.hashCode();
      }
    }
    
    protected int zzB()
    {
      int i = 0;
      int j = super.zzB();
      if (this.zzbiO != 0L) {
        j += zzrx.zzd(1, this.zzbiO);
      }
      if (!this.tag.equals("")) {
        j += zzrx.zzn(2, this.tag);
      }
      if ((this.zzbiT != null) && (this.zzbiT.length > 0))
      {
        int m = j;
        for (int n = 0; n < this.zzbiT.length; n++)
        {
          zzsi.zze localzze = this.zzbiT[n];
          if (localzze != null) {
            m += zzrx.zzc(3, localzze);
          }
        }
        j = m;
      }
      if (!Arrays.equals(this.zzbiV, zzsh.zzbiE)) {
        j += zzrx.zzb(6, this.zzbiV);
      }
      if (this.zzbiY != null) {
        j += zzrx.zzc(7, this.zzbiY);
      }
      if (!Arrays.equals(this.zzbiW, zzsh.zzbiE)) {
        j += zzrx.zzb(8, this.zzbiW);
      }
      if (this.zzbiU != null) {
        j += zzrx.zzc(9, this.zzbiU);
      }
      if (this.zzbiS) {
        j += zzrx.zzc(10, this.zzbiS);
      }
      if (this.zzbiQ != 0) {
        j += zzrx.zzA(11, this.zzbiQ);
      }
      if (this.zzbiR != 0) {
        j += zzrx.zzA(12, this.zzbiR);
      }
      if (!Arrays.equals(this.zzbiX, zzsh.zzbiE)) {
        j += zzrx.zzb(13, this.zzbiX);
      }
      if (!this.zzbiZ.equals("")) {
        j += zzrx.zzn(14, this.zzbiZ);
      }
      if (this.zzbja != 180000L) {
        j += zzrx.zze(15, this.zzbja);
      }
      if (this.zzbjb != null) {
        j += zzrx.zzc(16, this.zzbjb);
      }
      if (this.zzbiP != 0L) {
        j += zzrx.zzd(17, this.zzbiP);
      }
      if (!Arrays.equals(this.zzbjc, zzsh.zzbiE)) {
        j += zzrx.zzb(18, this.zzbjc);
      }
      if (this.zzbjd != 0) {
        j += zzrx.zzA(19, this.zzbjd);
      }
      if ((this.zzbje != null) && (this.zzbje.length > 0))
      {
        int k = 0;
        while (i < this.zzbje.length)
        {
          k += zzrx.zzlJ(this.zzbje[i]);
          i++;
        }
        j = j + k + 2 * this.zzbje.length;
      }
      return j;
    }
    
    public zzd zzFV()
    {
      this.zzbiO = 0L;
      this.zzbiP = 0L;
      this.tag = "";
      this.zzbiQ = 0;
      this.zzbiR = 0;
      this.zzbiS = false;
      this.zzbiT = zzsi.zze.zzFW();
      this.zzbiU = null;
      this.zzbiV = zzsh.zzbiE;
      this.zzbiW = zzsh.zzbiE;
      this.zzbiX = zzsh.zzbiE;
      this.zzbiY = null;
      this.zzbiZ = "";
      this.zzbja = 180000L;
      this.zzbjb = null;
      this.zzbjc = zzsh.zzbiE;
      this.zzbjd = 0;
      this.zzbje = zzsh.zzbix;
      this.zzbik = null;
      this.zzbiv = -1;
      return this;
    }
    
    public zzd zzJ(zzrw paramzzrw)
      throws IOException
    {
      for (;;)
      {
        int i = paramzzrw.zzFo();
        switch (i)
        {
        default: 
          if (zza(paramzzrw, i)) {}
          break;
        case 0: 
          return this;
        case 8: 
          this.zzbiO = paramzzrw.zzFq();
          break;
        case 18: 
          this.tag = paramzzrw.readString();
          break;
        case 26: 
          int i4 = zzsh.zzc(paramzzrw, 26);
          if (this.zzbiT == null) {}
          zzsi.zze[] arrayOfzze;
          for (int i5 = 0;; i5 = this.zzbiT.length)
          {
            arrayOfzze = new zzsi.zze[i4 + i5];
            if (i5 != 0) {
              System.arraycopy(this.zzbiT, 0, arrayOfzze, 0, i5);
            }
            while (i5 < -1 + arrayOfzze.length)
            {
              arrayOfzze[i5] = new zzsi.zze();
              paramzzrw.zza(arrayOfzze[i5]);
              paramzzrw.zzFo();
              i5++;
            }
          }
          arrayOfzze[i5] = new zzsi.zze();
          paramzzrw.zza(arrayOfzze[i5]);
          this.zzbiT = arrayOfzze;
          break;
        case 50: 
          this.zzbiV = paramzzrw.readBytes();
          break;
        case 58: 
          if (this.zzbiY == null) {
            this.zzbiY = new zzsi.zza();
          }
          paramzzrw.zza(this.zzbiY);
          break;
        case 66: 
          this.zzbiW = paramzzrw.readBytes();
          break;
        case 74: 
          if (this.zzbiU == null) {
            this.zzbiU = new zzsi.zzb();
          }
          paramzzrw.zza(this.zzbiU);
          break;
        case 80: 
          this.zzbiS = paramzzrw.zzFs();
          break;
        case 88: 
          this.zzbiQ = paramzzrw.zzFr();
          break;
        case 96: 
          this.zzbiR = paramzzrw.zzFr();
          break;
        case 106: 
          this.zzbiX = paramzzrw.readBytes();
          break;
        case 114: 
          this.zzbiZ = paramzzrw.readString();
          break;
        case 120: 
          this.zzbja = paramzzrw.zzFu();
          break;
        case 130: 
          if (this.zzbjb == null) {
            this.zzbjb = new zzsi.zzc();
          }
          paramzzrw.zza(this.zzbjb);
          break;
        case 136: 
          this.zzbiP = paramzzrw.zzFq();
          break;
        case 146: 
          this.zzbjc = paramzzrw.readBytes();
          break;
        case 152: 
          int i3 = paramzzrw.zzFr();
          switch (i3)
          {
          default: 
            break;
          case 0: 
          case 1: 
          case 2: 
            this.zzbjd = i3;
          }
          break;
        case 160: 
          int i1 = zzsh.zzc(paramzzrw, 160);
          if (this.zzbje == null) {}
          int[] arrayOfInt2;
          for (int i2 = 0;; i2 = this.zzbje.length)
          {
            arrayOfInt2 = new int[i1 + i2];
            if (i2 != 0) {
              System.arraycopy(this.zzbje, 0, arrayOfInt2, 0, i2);
            }
            while (i2 < -1 + arrayOfInt2.length)
            {
              arrayOfInt2[i2] = paramzzrw.zzFr();
              paramzzrw.zzFo();
              i2++;
            }
          }
          arrayOfInt2[i2] = paramzzrw.zzFr();
          this.zzbje = arrayOfInt2;
          break;
        case 162: 
          int j = paramzzrw.zzlC(paramzzrw.zzFv());
          int k = paramzzrw.getPosition();
          for (int m = 0; paramzzrw.zzFA() > 0; m++) {
            paramzzrw.zzFr();
          }
          paramzzrw.zzlE(k);
          if (this.zzbje == null) {}
          int[] arrayOfInt1;
          for (int n = 0;; n = this.zzbje.length)
          {
            arrayOfInt1 = new int[m + n];
            if (n != 0) {
              System.arraycopy(this.zzbje, 0, arrayOfInt1, 0, n);
            }
            while (n < arrayOfInt1.length)
            {
              arrayOfInt1[n] = paramzzrw.zzFr();
              n++;
            }
          }
          this.zzbje = arrayOfInt1;
          paramzzrw.zzlD(j);
        }
      }
    }
    
    public void zza(zzrx paramzzrx)
      throws IOException
    {
      int i = 0;
      if (this.zzbiO != 0L) {
        paramzzrx.zzb(1, this.zzbiO);
      }
      if (!this.tag.equals("")) {
        paramzzrx.zzb(2, this.tag);
      }
      if ((this.zzbiT != null) && (this.zzbiT.length > 0)) {
        for (int j = 0; j < this.zzbiT.length; j++)
        {
          zzsi.zze localzze = this.zzbiT[j];
          if (localzze != null) {
            paramzzrx.zza(3, localzze);
          }
        }
      }
      if (!Arrays.equals(this.zzbiV, zzsh.zzbiE)) {
        paramzzrx.zza(6, this.zzbiV);
      }
      if (this.zzbiY != null) {
        paramzzrx.zza(7, this.zzbiY);
      }
      if (!Arrays.equals(this.zzbiW, zzsh.zzbiE)) {
        paramzzrx.zza(8, this.zzbiW);
      }
      if (this.zzbiU != null) {
        paramzzrx.zza(9, this.zzbiU);
      }
      if (this.zzbiS) {
        paramzzrx.zzb(10, this.zzbiS);
      }
      if (this.zzbiQ != 0) {
        paramzzrx.zzy(11, this.zzbiQ);
      }
      if (this.zzbiR != 0) {
        paramzzrx.zzy(12, this.zzbiR);
      }
      if (!Arrays.equals(this.zzbiX, zzsh.zzbiE)) {
        paramzzrx.zza(13, this.zzbiX);
      }
      if (!this.zzbiZ.equals("")) {
        paramzzrx.zzb(14, this.zzbiZ);
      }
      if (this.zzbja != 180000L) {
        paramzzrx.zzc(15, this.zzbja);
      }
      if (this.zzbjb != null) {
        paramzzrx.zza(16, this.zzbjb);
      }
      if (this.zzbiP != 0L) {
        paramzzrx.zzb(17, this.zzbiP);
      }
      if (!Arrays.equals(this.zzbjc, zzsh.zzbiE)) {
        paramzzrx.zza(18, this.zzbjc);
      }
      if (this.zzbjd != 0) {
        paramzzrx.zzy(19, this.zzbjd);
      }
      if ((this.zzbje != null) && (this.zzbje.length > 0)) {
        while (i < this.zzbje.length)
        {
          paramzzrx.zzy(20, this.zzbje[i]);
          i++;
        }
      }
      super.zza(paramzzrx);
    }
  }
  
  public static final class zza
    extends zzry<zza>
  {
    public String[] zzbiF;
    public String[] zzbiG;
    public int[] zzbiH;
    public long[] zzbiI;
    
    public zza()
    {
      zzFS();
    }
    
    public boolean equals(Object paramObject)
    {
      boolean bool = false;
      if (paramObject == this) {
        bool = true;
      }
      for (;;)
      {
        return bool;
        if ((paramObject instanceof zza))
        {
          zza localzza = (zza)paramObject;
          if ((zzsc.equals(this.zzbiF, localzza.zzbiF)) && (zzsc.equals(this.zzbiG, localzza.zzbiG)) && (zzsc.equals(this.zzbiH, localzza.zzbiH)) && (zzsc.equals(this.zzbiI, localzza.zzbiI))) {
            if ((this.zzbik == null) || (this.zzbik.isEmpty()))
            {
              if ((localzza.zzbik == null) || (localzza.zzbik.isEmpty())) {
                bool = true;
              }
            }
            else {
              bool = this.zzbik.equals(localzza.zzbik);
            }
          }
        }
      }
    }
    
    public int hashCode()
    {
      int i = 31 * (31 * (31 * (31 * (31 * (527 + getClass().getName().hashCode()) + zzsc.hashCode(this.zzbiF)) + zzsc.hashCode(this.zzbiG)) + zzsc.hashCode(this.zzbiH)) + zzsc.hashCode(this.zzbiI));
      if ((this.zzbik == null) || (this.zzbik.isEmpty())) {}
      for (int j = 0;; j = this.zzbik.hashCode()) {
        return j + i;
      }
    }
    
    protected int zzB()
    {
      int i = 0;
      int j = super.zzB();
      int i6;
      int i7;
      if ((this.zzbiF != null) && (this.zzbiF.length > 0))
      {
        int i5 = 0;
        i6 = 0;
        i7 = 0;
        while (i5 < this.zzbiF.length)
        {
          String str2 = this.zzbiF[i5];
          if (str2 != null)
          {
            i7++;
            i6 += zzrx.zzfA(str2);
          }
          i5++;
        }
      }
      for (int k = j + i6 + i7 * 1;; k = j)
      {
        if ((this.zzbiG != null) && (this.zzbiG.length > 0))
        {
          int i2 = 0;
          int i3 = 0;
          int i4 = 0;
          while (i2 < this.zzbiG.length)
          {
            String str1 = this.zzbiG[i2];
            if (str1 != null)
            {
              i4++;
              i3 += zzrx.zzfA(str1);
            }
            i2++;
          }
          k = k + i3 + i4 * 1;
        }
        if ((this.zzbiH != null) && (this.zzbiH.length > 0))
        {
          int n = 0;
          int i1 = 0;
          while (n < this.zzbiH.length)
          {
            i1 += zzrx.zzlJ(this.zzbiH[n]);
            n++;
          }
          k = k + i1 + 1 * this.zzbiH.length;
        }
        if ((this.zzbiI != null) && (this.zzbiI.length > 0))
        {
          int m = 0;
          while (i < this.zzbiI.length)
          {
            m += zzrx.zzaa(this.zzbiI[i]);
            i++;
          }
          k = k + m + 1 * this.zzbiI.length;
        }
        return k;
      }
    }
    
    public zza zzFS()
    {
      this.zzbiF = zzsh.zzbiC;
      this.zzbiG = zzsh.zzbiC;
      this.zzbiH = zzsh.zzbix;
      this.zzbiI = zzsh.zzbiy;
      this.zzbik = null;
      this.zzbiv = -1;
      return this;
    }
    
    public zza zzG(zzrw paramzzrw)
      throws IOException
    {
      for (;;)
      {
        int i = paramzzrw.zzFo();
        switch (i)
        {
        default: 
          if (zza(paramzzrw, i)) {}
          break;
        case 0: 
          return this;
        case 10: 
          int i11 = zzsh.zzc(paramzzrw, 10);
          if (this.zzbiF == null) {}
          String[] arrayOfString2;
          for (int i12 = 0;; i12 = this.zzbiF.length)
          {
            arrayOfString2 = new String[i11 + i12];
            if (i12 != 0) {
              System.arraycopy(this.zzbiF, 0, arrayOfString2, 0, i12);
            }
            while (i12 < -1 + arrayOfString2.length)
            {
              arrayOfString2[i12] = paramzzrw.readString();
              paramzzrw.zzFo();
              i12++;
            }
          }
          arrayOfString2[i12] = paramzzrw.readString();
          this.zzbiF = arrayOfString2;
          break;
        case 18: 
          int i9 = zzsh.zzc(paramzzrw, 18);
          if (this.zzbiG == null) {}
          String[] arrayOfString1;
          for (int i10 = 0;; i10 = this.zzbiG.length)
          {
            arrayOfString1 = new String[i9 + i10];
            if (i10 != 0) {
              System.arraycopy(this.zzbiG, 0, arrayOfString1, 0, i10);
            }
            while (i10 < -1 + arrayOfString1.length)
            {
              arrayOfString1[i10] = paramzzrw.readString();
              paramzzrw.zzFo();
              i10++;
            }
          }
          arrayOfString1[i10] = paramzzrw.readString();
          this.zzbiG = arrayOfString1;
          break;
        case 24: 
          int i7 = zzsh.zzc(paramzzrw, 24);
          if (this.zzbiH == null) {}
          int[] arrayOfInt2;
          for (int i8 = 0;; i8 = this.zzbiH.length)
          {
            arrayOfInt2 = new int[i7 + i8];
            if (i8 != 0) {
              System.arraycopy(this.zzbiH, 0, arrayOfInt2, 0, i8);
            }
            while (i8 < -1 + arrayOfInt2.length)
            {
              arrayOfInt2[i8] = paramzzrw.zzFr();
              paramzzrw.zzFo();
              i8++;
            }
          }
          arrayOfInt2[i8] = paramzzrw.zzFr();
          this.zzbiH = arrayOfInt2;
          break;
        case 26: 
          int i3 = paramzzrw.zzlC(paramzzrw.zzFv());
          int i4 = paramzzrw.getPosition();
          for (int i5 = 0; paramzzrw.zzFA() > 0; i5++) {
            paramzzrw.zzFr();
          }
          paramzzrw.zzlE(i4);
          if (this.zzbiH == null) {}
          int[] arrayOfInt1;
          for (int i6 = 0;; i6 = this.zzbiH.length)
          {
            arrayOfInt1 = new int[i5 + i6];
            if (i6 != 0) {
              System.arraycopy(this.zzbiH, 0, arrayOfInt1, 0, i6);
            }
            while (i6 < arrayOfInt1.length)
            {
              arrayOfInt1[i6] = paramzzrw.zzFr();
              i6++;
            }
          }
          this.zzbiH = arrayOfInt1;
          paramzzrw.zzlD(i3);
          break;
        case 32: 
          int i1 = zzsh.zzc(paramzzrw, 32);
          if (this.zzbiI == null) {}
          long[] arrayOfLong2;
          for (int i2 = 0;; i2 = this.zzbiI.length)
          {
            arrayOfLong2 = new long[i1 + i2];
            if (i2 != 0) {
              System.arraycopy(this.zzbiI, 0, arrayOfLong2, 0, i2);
            }
            while (i2 < -1 + arrayOfLong2.length)
            {
              arrayOfLong2[i2] = paramzzrw.zzFq();
              paramzzrw.zzFo();
              i2++;
            }
          }
          arrayOfLong2[i2] = paramzzrw.zzFq();
          this.zzbiI = arrayOfLong2;
          break;
        case 34: 
          int j = paramzzrw.zzlC(paramzzrw.zzFv());
          int k = paramzzrw.getPosition();
          for (int m = 0; paramzzrw.zzFA() > 0; m++) {
            paramzzrw.zzFq();
          }
          paramzzrw.zzlE(k);
          if (this.zzbiI == null) {}
          long[] arrayOfLong1;
          for (int n = 0;; n = this.zzbiI.length)
          {
            arrayOfLong1 = new long[m + n];
            if (n != 0) {
              System.arraycopy(this.zzbiI, 0, arrayOfLong1, 0, n);
            }
            while (n < arrayOfLong1.length)
            {
              arrayOfLong1[n] = paramzzrw.zzFq();
              n++;
            }
          }
          this.zzbiI = arrayOfLong1;
          paramzzrw.zzlD(j);
        }
      }
    }
    
    public void zza(zzrx paramzzrx)
      throws IOException
    {
      int i = 0;
      if ((this.zzbiF != null) && (this.zzbiF.length > 0)) {
        for (int m = 0; m < this.zzbiF.length; m++)
        {
          String str2 = this.zzbiF[m];
          if (str2 != null) {
            paramzzrx.zzb(1, str2);
          }
        }
      }
      if ((this.zzbiG != null) && (this.zzbiG.length > 0)) {
        for (int k = 0; k < this.zzbiG.length; k++)
        {
          String str1 = this.zzbiG[k];
          if (str1 != null) {
            paramzzrx.zzb(2, str1);
          }
        }
      }
      if ((this.zzbiH != null) && (this.zzbiH.length > 0)) {
        for (int j = 0; j < this.zzbiH.length; j++) {
          paramzzrx.zzy(3, this.zzbiH[j]);
        }
      }
      if ((this.zzbiI != null) && (this.zzbiI.length > 0)) {
        while (i < this.zzbiI.length)
        {
          paramzzrx.zzb(4, this.zzbiI[i]);
          i++;
        }
      }
      super.zza(paramzzrx);
    }
  }
  
  public static final class zze
    extends zzry<zze>
  {
    private static volatile zze[] zzbjf;
    public String key;
    public String value;
    
    public zze()
    {
      zzFX();
    }
    
    public static zze[] zzFW()
    {
      if (zzbjf == null) {}
      synchronized (zzsc.zzbiu)
      {
        if (zzbjf == null) {
          zzbjf = new zze[0];
        }
        return zzbjf;
      }
    }
    
    public boolean equals(Object paramObject)
    {
      boolean bool = false;
      if (paramObject == this) {
        bool = true;
      }
      for (;;)
      {
        return bool;
        if ((paramObject instanceof zze))
        {
          zze localzze = (zze)paramObject;
          if (this.key == null)
          {
            if (localzze.key != null) {
              continue;
            }
            label37:
            if (this.value != null) {
              break label107;
            }
            if (localzze.value != null) {
              continue;
            }
          }
          for (;;)
          {
            if ((this.zzbik == null) || (this.zzbik.isEmpty()))
            {
              if ((localzze.zzbik != null) && (!localzze.zzbik.isEmpty())) {
                break;
              }
              bool = true;
              break;
              if (this.key.equals(localzze.key)) {
                break label37;
              }
              break;
              label107:
              if (!this.value.equals(localzze.value)) {
                break;
              }
            }
          }
          bool = this.zzbik.equals(localzze.zzbik);
        }
      }
    }
    
    public int hashCode()
    {
      int i = 0;
      int j = 31 * (527 + getClass().getName().hashCode());
      int k;
      int n;
      label47:
      int i1;
      if (this.key == null)
      {
        k = 0;
        int m = 31 * (k + j);
        if (this.value != null) {
          break label90;
        }
        n = 0;
        i1 = 31 * (n + m);
        if ((this.zzbik != null) && (!this.zzbik.isEmpty())) {
          break label102;
        }
      }
      for (;;)
      {
        return i1 + i;
        k = this.key.hashCode();
        break;
        label90:
        n = this.value.hashCode();
        break label47;
        label102:
        i = this.zzbik.hashCode();
      }
    }
    
    protected int zzB()
    {
      int i = super.zzB();
      if (!this.key.equals("")) {
        i += zzrx.zzn(1, this.key);
      }
      if (!this.value.equals("")) {
        i += zzrx.zzn(2, this.value);
      }
      return i;
    }
    
    public zze zzFX()
    {
      this.key = "";
      this.value = "";
      this.zzbik = null;
      this.zzbiv = -1;
      return this;
    }
    
    public zze zzK(zzrw paramzzrw)
      throws IOException
    {
      for (;;)
      {
        int i = paramzzrw.zzFo();
        switch (i)
        {
        default: 
          if (zza(paramzzrw, i)) {}
          break;
        case 0: 
          return this;
        case 10: 
          this.key = paramzzrw.readString();
          break;
        case 18: 
          this.value = paramzzrw.readString();
        }
      }
    }
    
    public void zza(zzrx paramzzrx)
      throws IOException
    {
      if (!this.key.equals("")) {
        paramzzrx.zzb(1, this.key);
      }
      if (!this.value.equals("")) {
        paramzzrx.zzb(2, this.value);
      }
      super.zza(paramzzrx);
    }
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/com/google/android/gms/internal/zzsi.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */