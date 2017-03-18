
/** The DemoSimulator class drives a simulation of a registrar's office. The class
 * runs the simulation for 2, 3, 4, and 5 registrars and for queue and stack based
 * lines. It consists of an outer loop that runs for each number of registrars and
 * an inner loop that runs for at least 480 iterations that represents the number
 * of minutes the office is open. It keeps track of average waiting time, average processing
 * time, longest wait, and number of students for each variation of the simulation.
 * 
 */
public class Main {


    public static void main(String[] args) {
        int[] registrarArray;

        //outer loop to simulate different numbers of registrars
        for(int numRegistrars = 2; numRegistrars <= 5; numRegistrars++) {
            registrarArray = new int[numRegistrars];

            //boolean stillProcessing = true;
            int time = 1;
            int stillProcessing = 1; //1 indicates still within 480 minutes
            //2 indicates after 480 but still processing
            //0 indicates done
            while(stillProcessing != 0) {
                if(stillProcessing == 1 && time>480) //if office is now closed
                    stillProcessing = 2; //update flag

                if(stillProcessing == 1) {
                    //add students
                }




                time++;
            }
        }
    }

    /** The generateNumStudents method determines how many students enter the line
     * at a particular minute. It takes as an argument an integer from 1-100, inclusive,
     * indicating the random value generated for the current minute. It returns an integer
     * indicating the number of students who arrive based on a provided set of probabilities.
     * 4 students - 3%
     * 3 students - 13%
     * 2 students - 21%
     *
     *
     */
    public int generateNumStudents(int probability) {
        if(probability<3) {
            return 4;
        }
        else if(probability<16) {
            return 3;
        }
        else if(probability<37) {
            return 2;
        }
        else if(probability<65) {
            return 1;
        }
        else {
            return 0;
        }
    }
}