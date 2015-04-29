package sharedResources;

//import components.Main;

import components.Master;
import components.Service;

import java.net.InetAddress;
import java.util.List;

/**
 * Created by Krishna on 4/22/2015.
 */
public class Slave implements Runnable {

    public InetAddress ip;
    public int port = Service.RMIRegistryPort;
    public Job jobAssigned;
    public int failCount=0; //keep track of failed or over delay count
    public SlaveStatus status;
    public Service service;
    public Master master;
    public static int jobCountThreshold = 5;
    public long averageServiceTime, bestTime, worstTime;
    public long lastSeen;
    public long totalPushTime, totalPullTime;
    public int totalJobsPushed, totalJobsPulled;

    public Job toBePushed;
    public List<Job> jobHistory;
    public List<Job> inProcessJobs;
    public List<Job> completedJobs;
    public List<Job> updatedJobs;

    public boolean pushMode = false, pullMode = false;


    public Slave(InetAddress ip) {
        this.ip = ip;
    }

    @Override
    public void run(){
        if(pushMode)
        {
            pushJobData();
            pushMode = false;
        }
        else if(pullMode)
        {
            pullNextSortedJob();
            pullMode = false;
        }
    }

    public boolean pushJobData()
    {
        /**
         * push data from file to server
         * add toBePushed to the inProgressJob list
         */

        return true;
    }

    public boolean isReadyToPush() {
        if( !pullMode && status.equals(SlaveStatus.Active) && toBePushed == null && inProcessJobs.size()+completedJobs.size() < jobCountThreshold)
            return true;
        return false;
    }

    public boolean updateCompetedList()
    {
        /**
         * get the newly completed jobs from server and update
         */
        return false;
    }

    public boolean pullNextSortedJob()
    {
        /**
         * get the next completed Job from server and update it to the file
         * remove it from completed list
         * update Job status
         */
        return true;
    }

    public void slaveNotResponsive()
    {
        /**
         * update Jobs about this
         * updates Slave status
         */
    }

    public void prepareToPush(Job bestJob) {
        pushMode = true;
        SharedResources.slave_PullQueue.remove(this);
    }

    public void prepareToPull() {
        pullMode = true;
        SharedResources.slave_PullQueue.remove(this);
    }

    public boolean isReadyToPull() {
        if( ! pushMode && ! completedJobs.isEmpty() && status.equals(SlaveStatus.Active))
            return true;
        return false;
    }

    public double getAveragePushTime()
    {
        return totalPushTime/totalJobsPushed;
    }

    public double getAveragePullTime()
    {
        return totalPullTime/totalJobsPulled;
    }

    public InetAddress getIp() {
        return ip;
    }

    public void setIp(InetAddress ip) {
        this.ip = ip;
    }

    public long getAverageServiceTime() {
        return averageServiceTime;
    }

    public void setAverageServiceTime(long averageServiceTime) {
        this.averageServiceTime = averageServiceTime;
    }

    public long getBestTime() {
        return bestTime;
    }

    public void setBestTime(long bestTime) {
        this.bestTime = bestTime;
    }

    public List<Job> getCompletedJobs() {
        return completedJobs;
    }

    public void setCompletedJobs(List<Job> completedJobs) {
        this.completedJobs = completedJobs;
    }

    public int getFailCount() {
        return failCount;
    }

    public void setFailCount(int failCount) {
        this.failCount = failCount;
    }

    public Job getJobAssigned() {
        return jobAssigned;
    }

    public void setJobAssigned(Job jobAssigned) {
        this.jobAssigned = jobAssigned;
    }

    public long getLastSeen() {
        return lastSeen;
    }

    public void setLastSeen(long lastSeen) {
        this.lastSeen = lastSeen;
    }

    public Master getMaster() {
        return master;
    }

    public void setMaster(Master master) {
        this.master = master;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public Service getService() {
        return service;
    }

    public void setService(Service service) {
        this.service = service;
    }

    public SlaveStatus getStatus() {
        return status;
    }

    public void setStatus(SlaveStatus status) {
        this.status = status;
    }

    public long getWorstTime() {
        return worstTime;
    }

    public void setWorstTime(long worstTime) {
        this.worstTime = worstTime;
    }




}
