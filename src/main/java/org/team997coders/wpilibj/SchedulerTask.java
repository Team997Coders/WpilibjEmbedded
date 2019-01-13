package org.team997coders.wpilibj;

import edu.wpi.first.wpilibj.command.Scheduler;

/**
 * This class implements, as a thread, a task to pump the WPILib scheduler.
 */
public class SchedulerTask extends Thread {
  private final Scheduler m_scheduler;
  private final long pulse = 20;

  /**
   * Initializes a task with an instance of the scheduler. You can pass in
   * {@link Scheduler#getInstance() scheduler} instance.
   * 
   * @param scheduler An instance of a Scheduler
   */
  public SchedulerTask(Scheduler scheduler) {
    super();
    if (scheduler == null) {
      throw new NullPointerException("scheduler cannot be null. Use Scheduler.getInstance().");
    }
    m_scheduler = scheduler;
  }

  /**
   * Run the task, which is run when the thread is started.
   */
  public void run() {
    long nextRunTime = nextPulse();
    while (true) {
      if (System.currentTimeMillis() >= nextRunTime) {
        nextRunTime = nextPulse();
        m_scheduler.run();
      }
    }
  }

  private long nextPulse() {
    return System.currentTimeMillis() + pulse;
  }
}
