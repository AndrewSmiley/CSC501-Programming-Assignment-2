import java.util.HashMap;
import java.util.Map;

/**
 * This class drives a simulation of a registrar's office and uses part of Eric's DemoSimulator code.  The class
 * runs the simulation for 2, 3, 4, and 5 registrars and for queue and stack based
 * lines. To run the simulation against CSCQueue, run runQueueSimulation(), against stack, runStackSimulation()
 * The random number generation was grabbed from here
 * http://stackoverflow.com/questions/363681/how-to-generate-random-integers-within-a-specific-range-in-java
 */
public class Main {

    /**
     * This class is a simple helper class that allows us to track an individual student's time with a registrar
     * Basically we hold the time entered and the time we know (based upon our time generator) it will take to processs
     * Really the biggest selling point here is the fact that we can determine whether a student is done with a registrar or not
     */
    static class RegistrarStudent {
        /**
         * The time a student started working wiht the registar
         */
        private int timeEntered;
        /**
         * The time it will take the registar to finish servicing the student
         */
        private int timeToCompletion;
        /**
         * Simple constructor that allows us to record a student being helped by a registrar
         *
         * @param timeEntered      the time started with the registrar
         * @param timeToCompletion the time until the student is done with a registrar
         */
        RegistrarStudent(int timeEntered, int timeToCompletion) {
            this.timeEntered = timeEntered;
            this.timeToCompletion = timeToCompletion;
        }

        /**
         * Simple method to determine if a registrar is done with a student
         *
         * @param currentTime the current time
         * @return true if they are done, false otherwise
         */
        public boolean isFinished(int currentTime) {
            return currentTime - this.timeEntered > this.timeToCompletion;
        }
    }

    public static void main(String[] args) {

        //Run the stack Simulation First
        Main.runStackSimulation();

        //next run the queue simulation
        Main.runQueueSimulation();

    }

    /**
     * The generateNumStudents method determines how many students enter the line
     * at a particular minute. It takes as an argument an integer from 1-100, inclusive,
     * indicating the random value generated for the current minute. It returns an integer
     * indicating the number of students who arrive based on a provided set of probabilities.
     * 4 students - 3%
     * 3 students - 13%
     * 2 students - 21%
     */
    public static int generateNumStudents(int probability) {
        if (probability < 3) {
            return 4;
        } else if (probability < 16) {
            return 3;
        } else if (probability < 37) {
            return 2;
        } else if (probability < 65) {
            return 1;
        } else {
            return 0;
        }
    }

    /**
     * This is just a simple method to determine the amount of time it will take to finish with a registrar
     * @param probability the randomly generated probability to determine the processing time in minutes
     * @return the processing time in minutes
     */
    public static int generateProcessingTime(int probability) {
        if (probability < 2) {
            return 6;
        } else if (probability < 6) {
            return 5;
        } else if (probability < 14) {
            return 4;
        } else if (probability < 37) {
            return 3;
        } else if (probability < 70) {
            return 2;
        } else{
            return 1;
        }
    }

    public static void runStackSimulation() {
        //instanciate our stack
        CSCStack stack = new CSCStack();
        //Just some info text and our columns
        System.out.println("\n\nRunning simulation using Stack");
        //check out that badass formatting... Thank you Java... Lousy piece of.....,
        System.out.printf("%-22s%-22s%-22s%-22s%-22s%-22s%-22s\n", "Registrars", "Students", "Total Time","Total Waiting Time", "Avg Wait", "Avg Process", "Longest");
        //outer loop to simulate different numbers of registrars
        for (int numRegistrars = 2; numRegistrars <= 5; numRegistrars++) {
            //this is a hashmap containing the registrar number and the student the registrar is currently helping
            //this is somewhat wonky... Basically, the key is the registrar number and the RegistrarStudent object
            //allows us to monitor whether the student is still with theregistrar or not.
            //I could have done an array implementation but this seemed simpler to me when i wrote it. In hindsight that was a
            //mistake...
            Map<Integer, RegistrarStudent> map = new HashMap<Integer, RegistrarStudent>();
            int time = 1;
            boolean proccessing = true; // boolean to determine if we are still processing students or not
            int totalWaitTime = 0; // the total wait time
            int totalProcessTime = 0;// the total process time
            int studentsProcessed = 0; // the total number of students we have serviced
            int longestWait = 0;// the longest wait
            double averageWait, averageProcessingTime; // the average wait and process times
            while (proccessing) { //basically
                //only students can join the line if the time is < 480
                if (time < 480) {
                    //get the probability for the number of students at a time
                    int studentsProbability = (int) (Math.random() * ( 100 - 0 ));

                    //first, determine how many students enter for this mnute
                    int students = Main.generateNumStudents(studentsProbability);
                    //add the students to the line
                    for (int j = 0; j < students; j++) {
                        stack.push(time);
                    }

                }
                //get the probablity for the time
                int timeProbability =  (int) (Math.random() * ( 100 - 0 ));
                //next, get the process time for the next student
                int processTime = Main.generateProcessingTime(timeProbability);
                //add this batch of process time to the total processtime
                totalProcessTime = processTime + totalProcessTime; //add the process time
                //finally, see if any registrars are open
                for (int k = 0; k < numRegistrars; k++) {
                    //if the registrar has a student, check to see if they are finished, if so, pop and add the current student
                    if (map.containsKey(k)) {
                        //if the registrar is done with the student
                        if (map.get(k).isFinished(time)) {
                            map.remove(k); //remove the current student from the registrar
                            studentsProcessed++;
                            //this has to be in a try catch because if we attempt to pop an empty stack we get an exception,
                            //i.e. if there are no students in line, there is no reason to
                            try {
                                //attempt to get another student from the line, exception is thrown if the line is empty
                                int newWait = stack.pop();
                                if (time - newWait > longestWait) {
                                    longestWait = (time - newWait);
                                }
                                totalWaitTime = (time - newWait) + totalWaitTime;//add the next value of wait time
                                //give the student a registrar
                                map.put(k, new RegistrarStudent(time, processTime));
                            } catch (Exception e) {
                            }

                        }

                    } else {

                        //this has to be in a try catch because if we attempt to pop an empty stack we get an exception,
                        //i.e. if there are no students in line, there is no reason to
                        try {
                            int newWait = stack.pop();//get another student from the line
                            //check to see if their wait was the longest
                            if (time - newWait > longestWait) {
                                longestWait = (time - newWait);
                            }
                            totalWaitTime = (time - newWait) + totalWaitTime;//add the next value of wait time
                            //if there is a student in the line, we will get here
                            //assign them a registrar
                            map.put(k, new RegistrarStudent(time, processTime));
                        } catch (Exception e) {
                        }

                    }

                }
                //if there are no students in the line and it is passed closing then we can close the office
                if (stack.isEmpty() && time >= 480 && map.isEmpty()) {
                    proccessing = false;
                }
                time++;//increment to the next minute
            }

            //output
            System.out.printf("%-22s%-22s%-22s%-22s%-22s%-22s%-22s\n", numRegistrars, studentsProcessed, time,totalWaitTime, String.format("%.2f", (double) totalWaitTime / studentsProcessed), String.format("%.2f", (double) totalProcessTime / studentsProcessed), longestWait);


        }
    }

    public static void runQueueSimulation() {
        //instanciate our queue
//        CSCStack stack = new CSCStack();
        CSCQueue queue = new CSCQueue();
        //Just some info text and our columns
        System.out.println("Running simulation using Queue");
        //check out that badass formatting... Thank you Java... Lousy piece of.....,
        System.out.printf("%-22s%-22s%-22s%-22s%-22s%-22s%-22s\n", "Registrars", "Students", "Total Time","Total Waiting Time", "Avg Wait", "Avg Process", "Longest");
        //outer loop to simulate different numbers of registrars
        for (int numRegistrars = 2; numRegistrars <= 5; numRegistrars++) {
            //this is a hashmap containing the registrar number and the student the registrar is currently helping
            //this is somewhat wonky... Basically, the key is the registrar number and the RegistrarStudent object
            //allows us to monitor whether the student is still with the registrar or not.
            //I could have done an array implementation but this seemed simpler to me when i wrote it. In hindsight that was a
            //mistake...
            Map<Integer, RegistrarStudent> map = new HashMap<Integer, RegistrarStudent>();
            int time = 1;
            boolean proccessing = true; // boolean to determine if we are still processing students or not
            int totalWaitTime = 0; // the total wait time
            int totalProcessTime = 0;// the total process time
            int studentsProcessed = 0; // the total number of students we have serviced
            int longestWait = 0;// the longest wait
            double averageWait, averageProcessingTime; // the average wait and process times
            while (proccessing || time < 480) {
                //only students can join the line if the time is < 480
                if (time < 480) {
                    //get the probability for the number of students at a time
                    int studentsProbability = (int) (Math.random() * ( 100 - 0 ));

                    //first, determine how many students enter for this mnute
                    int students = Main.generateNumStudents(studentsProbability);
                    //add the students to the line
                    //remember, the CSCQueue throws an exception if we try to add something when we have run out of space.
                    //wrap this whole thing in a try catch
                    try {
                        for (int j = 0; j < students; j++) {
                            queue.enqueue(time);
                        }
                    } catch (Exception e) {

                    }


                }
                //get the probablity for the time
                int timeProbability = (int) (Math.random() * ( 100 - 0 ));
                //next, get the process time for the next student
                int processTime = Main.generateProcessingTime(timeProbability);
                //add this batch of process time to the total processtime
                totalProcessTime = processTime + totalProcessTime; //add the process time
                //finally, see if any registrars are open
                for (int k = 0; k < numRegistrars; k++) {
                    //if the registrar has a student, check to see if they are finished, if so, pop and add the current student
                    if (map.containsKey(k)) {
                        //if the registrar is done with the student
                        if (map.get(k).isFinished(time)) {
                            map.remove(k); //remove the current student from the registrar
                            studentsProcessed++;
                            //this has to be in a try catch because if we attempt to pop an empty stack we get an exception,
                            //i.e. if there are no students in line, there is no reason to
                            try {
                                //attempt to get another student from the line, exception is thrown if the line is empty
                                int newWait = queue.dequeue();
                                if (time - newWait > longestWait) {
                                    longestWait = (time - newWait);
                                }
                                totalWaitTime = (time - newWait) + totalWaitTime;//add the next value of wait time
                                //give the student a registrar
                                map.put(k, new RegistrarStudent(time, processTime));
                            } catch (Exception e) {
                            }

                        }

                    } else {

                        //this has to be in a try catch because if we attempt to pop an empty stack we get an exception,
                        //i.e. if there are no students in line, there is no reason to
                        try {
                            int newWait = queue.dequeue();//get another student from the line
                            //check to see if their wait was the longest
                            if (time - newWait > longestWait) {
                                longestWait = (time - newWait);
                            }
                            totalWaitTime = (time - newWait) + totalWaitTime;//add the next value of wait time
                            //if there is a student in the line, we will get here
                            //assign them a registrar
                            map.put(k, new RegistrarStudent(time, processTime));
                        } catch (Exception e) {
                        }

                    }

                }
                //if there are no students in the line and it is passed closing then we can close the office
                if (queue.isEmpty() && time >= 480) {
                    proccessing = false;
                }
                time++;//increment to the next minute
            }

            //output
            System.out.printf("%-22s%-22s%-22s%-22s%-22s%-22s%-22s\n", numRegistrars, studentsProcessed, time,totalWaitTime, String.format("%.2f", (double) totalWaitTime / studentsProcessed), String.format("%.2f", (double) totalProcessTime / studentsProcessed), longestWait);


        }
    }


}