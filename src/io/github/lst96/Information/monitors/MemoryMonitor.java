package io.github.lst96.Information.monitors;

public class MemoryMonitor
{
  public final int getFreeRam()
  {
    Runtime runtime = Runtime.getRuntime();
    return Math.round((float)(runtime.freeMemory() / 1048576L));
  }

  public final int getMaxRam()
  {
    Runtime runtime = Runtime.getRuntime();
    return Math.round((float)(runtime.maxMemory() / 1048576L));
  }

  public final int getUsedRam()
  {
    return getTotalRam() - getFreeRam();
  }

  public final int getTotalRam()
  {
    Runtime runtime = Runtime.getRuntime();
    return Math.round((float)(runtime.totalMemory() / 1048576L));
  }
}