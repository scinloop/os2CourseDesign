package com.cyt.os.kernel.process.algorithm;

import com.cyt.os.common.Config;
import com.cyt.os.enums.ProcessStatus;
import com.cyt.os.kernel.process.data.PCB;
import org.apache.log4j.Logger;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.TimeUnit;

/**
 * 优先级最高
 *
 * @author cyt
 * @date 2023/11/27
 */
public class PJF extends ProcessSchedulingAlgorithm {

    private static final Logger log = Logger.getLogger(FCFS.class);

    public PJF(List<PCB> readyQueue) {
        super(readyQueue, log);
    }

    @Override
    public void run() {
        while (!readyQueue.isEmpty()) {
            try {
                TimeUnit.MILLISECONDS.sleep(Config.WAIT_PROCESS_200);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            // 取出到达时间最小
            // TODO ACTIVE_READY
            Optional<PCB> maxPriority = readyQueue.stream().
                    filter(pcb -> pcb.getStatus() == ProcessStatus.ACTIVE_READY).
                    max(Comparator.comparingInt(PCB::getPriority));
            if (maxPriority.isPresent()) {
                PCB min = maxPriority.get();
                PCB pcb = removePCB(min);
                executeProcess(pcb);
            }
        }
    }
}

