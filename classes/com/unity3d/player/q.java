package com.unity3d.player;

import android.os.Build.VERSION;

public final class q
{
  static final boolean a;
  static final boolean b;
  static final boolean c;
  static final boolean d;
  static final boolean e;
  static final boolean f;
  static final boolean g;
  static final boolean h;
  static final f i;
  static final e j;
  static final h k;
  static final g l;
  static final i m;
  
  static
  {
    n localn = null;
    boolean bool1 = true;
    boolean bool2;
    boolean bool3;
    label28:
    boolean bool4;
    label43:
    boolean bool5;
    label59:
    boolean bool6;
    label75:
    boolean bool7;
    label91:
    boolean bool8;
    label107:
    label120:
    d locald;
    label139:
    c localc;
    label159:
    l locall;
    if (Build.VERSION.SDK_INT >= 11)
    {
      bool2 = bool1;
      a = bool2;
      if (Build.VERSION.SDK_INT < 12) {
        break label228;
      }
      bool3 = bool1;
      b = bool3;
      if (Build.VERSION.SDK_INT < 14) {
        break label233;
      }
      bool4 = bool1;
      c = bool4;
      if (Build.VERSION.SDK_INT < 16) {
        break label239;
      }
      bool5 = bool1;
      d = bool5;
      if (Build.VERSION.SDK_INT < 17) {
        break label245;
      }
      bool6 = bool1;
      e = bool6;
      if (Build.VERSION.SDK_INT < 19) {
        break label251;
      }
      bool7 = bool1;
      f = bool7;
      if (Build.VERSION.SDK_INT < 21) {
        break label257;
      }
      bool8 = bool1;
      g = bool8;
      if (Build.VERSION.SDK_INT < 23) {
        break label263;
      }
      h = bool1;
      if (!a) {
        break label268;
      }
      locald = new d();
      i = locald;
      if (!b) {
        break label274;
      }
      localc = new c();
      j = localc;
      if (!d) {
        break label280;
      }
      locall = new l();
      label179:
      k = locall;
      if (!e) {
        break label286;
      }
    }
    label228:
    label233:
    label239:
    label245:
    label251:
    label257:
    label263:
    label268:
    label274:
    label280:
    label286:
    for (k localk = new k();; localk = null)
    {
      l = localk;
      if (h) {
        localn = new n();
      }
      m = localn;
      return;
      bool2 = false;
      break;
      bool3 = false;
      break label28;
      bool4 = false;
      break label43;
      bool5 = false;
      break label59;
      bool6 = false;
      break label75;
      bool7 = false;
      break label91;
      bool8 = false;
      break label107;
      bool1 = false;
      break label120;
      locald = null;
      break label139;
      localc = null;
      break label159;
      locall = null;
      break label179;
    }
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/com/unity3d/player/q.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */