package components;

import sharedResources.SharedResources;
import sharedResources.Slave;

/**
 * Created by Krishna on 4/22/2015.
 */
public class Allocator implements Runnable{

    public static int TASK_PRIORITY = 1;

    public void run()
    {
        while( !Master.done)
        {
            if(! SharedResources.job_OpenQueue.isEmpty())
            {
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            else
            {
                Slave suitableSlave = SharedResources.slave_PushQueue.peek();

                if(suitableSlave.isReadyToPush())
                {
                    SharedResources.slave_PushQueue.remove(suitableSlave);
                    suitableSlave.prepareToPush(SharedResources.job_OpenQueue.poll());
                    SharedResources.executor.execute(suitableSlave);
                }
            }

        }
    }
}
