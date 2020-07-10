package com.concurrencysample;

import software.amazon.codeguruprofilerjavaagent.Profiler;



public class ConcurrencyCheckout {

     

    public static void main(final String[] args) {
        // Start the profiler
        Profiler systemProfiler = Profiler.builder().profilingGroupName("concurrencysample-profiler").build();
        systemProfiler.start();
         
        // int thrad_count = 5;
        // int make_count =0 ;
        System.out.println("================================");
        System.out.println("Start Concurrency Make Erro Test");
        System.out.println("================================\n");

        BasicSynchronization[] basicsynchronizationThreadList = new BasicSynchronization[8];

        for (int i = 0; i < 8; i++) {
            basicsynchronizationThreadList[i] = new BasicSynchronization(0);
            basicsynchronizationThreadList[i].start();
        }
        BasicCheckMap basiccheckmapthread1 = new BasicCheckMap(0);
        basiccheckmapthread1.start();

        BasicCheckMap basiccheckmapthread2 = new BasicCheckMap(0);
        basiccheckmapthread2.start();

        /*
        int loop_count = 0;
        while(true){
            loop_count++;
//            System.out.println("================================");
//            System.out.println("start : " + loop_count);

            while (true) {
                boolean doing = false;
                
                for (int i = 0; i < basicsynchronizationThreadList.length; i++) {
                    if (basicsynchronizationThreadList[i].getState() != Thread.State.TERMINATED)
                        doing = true;
                }
                if (!doing)
                    break;
            }
            for (int i = 0; i < basicsynchronizationThreadList.length; i++) {
                make_count += basicsynchronizationThreadList[i].getCount();
                basicsynchronizationThreadList[i].setCount(0);
                basicsynchronizationThreadList[i].setLoopCount((int) (Math.random() * 1000000));
                
            }
//            System.out.println("Try put Count : " + make_count);
//            System.out.println("Make Value : " + SingletonRepo.getInstance().getMapCount());
//            System.out.println(loop_count + " : Test end!");
//            System.out.println("================================");
        
        }
*/

    }

}
