/* Your country has an infinite number of lakes... Initially, all the lakes are empty, but when it rains over the nth lake, the nth lake becomes full of water... If it rains over a lake that is full of water, there will be a flood. Your goal is to avoid floods in any lake...Given an integer array rains where:
    * rains[i] > 0 means there will be rains over the rains[i] lake...
    * rains[i] == 0 means there are no rains this day and you can choose one lake this day and dry it.
Return an array ans where:
    * ans.length == rains.length...
    * ans[i] == -1 if rains[i] > 0...
    * ans[i] is the lake you choose to dry in the ith day if rains[i] == 0...
If there are multiple valid answers return any of them... If it is impossible to avoid flood return an array of zero... Notice that if you chose to dry a full lake, it becomes empty, but if you chose to dry an empty lake, nothing changes... We know the the array beforehand... 
   * Eg 1: Rain = [1, 2, 3, 1]               ans = []
   * Eg 2: Rain = [1, 2, 0, 0, 1, 2]         ans = [-1, -1, 1, 2, -1, -1]
   * Eg 3: Rain = [1, 2, 0, 0, 1]            ans = []*/
import java.util.*;
public class Rain 
{
    public int[] AvoidFlood(int rain[])
    {
        int ans[] = new int[rain.length];
        int array[] = {};    // empty array for call...
        Hashtable<Integer, Integer> lakes = new Hashtable<Integer, Integer>(rain.length, 1);
        List<Integer> indices = new ArrayList<Integer>();
        int i = 0, dry = 0, k = 0;
        do
        {
            if(rain[i] > 0)     // Checking if there was rain...
            {
                lakes.put(rain[i], 1);          // Adding to the Hashtable...
                System.out.println("A"+lakes);
                ans[i] = -1;    // Updating answer value...
            }
            if(lakes.containsKey(rain[i]) && dry > 0)    // If at least one day was dry, and current lake is full...
            {
                lakes.remove(rain[i]);           // Removing the current lake from the Hashtable...
                System.out.println("R"+lakes);
                dry--;
                ans[indices.get(k)] = rain[i];    // Adding the values...
                k++;
            }
            else if(rain[i] == 0)
            {
                indices.add(i);     // Adding the index values of the dry days in the list...
                dry++;
            }
            i++;
        }while(i < rain.length);
        for(int j = 0; j < ans.length; j++)
            System.out.print(ans[j]+", ");
        System.out.println();
        if(lakes.isEmpty())    // If Hashtable is empty...
            return ans;
        return array;      // If the Hashtable is not empty...
    }
    public static void main(String[] args)
    {
        Scanner sc = new Scanner(System.in);
        int x;
        System.out.print("Enter the length of the rains array : ");
        x = sc.nextInt();
        int rain[] = new int[x];
        int answer[] = new int[x];
        for(int i = 0; i < x; i++)
        {
            System.out.print("Enter the "+(i+1)+" th Rain : ");
            rain[i] = sc.nextInt();
        }
        Rain rains = new Rain();             // Object creation...
        answer = rains.AvoidFlood(rain);        // Function call...
        System.out.println("The Array formed is : ");
        System.out.print("[");
        for(int i = 0; i < answer.length; i++)
            System.out.print(answer[i]+", ");
        System.out.println("]");
        sc.close();
    }
}

// Time Complexity  - O(n) time...         n = number of days
// Space Complexity - O(n) space...        Hashtable of n data keys created...

/* DEDUCTIONS :- 
 * 1. Since we know beforehand when and where it will rain, we can dry up a specific filled lake before it rains over that lake...
 * 2. Since drying a dried up lake will not be beneficial we must treat the problem as a set of optimal sub-problems... 
*/