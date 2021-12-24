package pack;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class MyFruitBasket {

    public static void main(String[] args) throws IOException, CsvException {

        String fileName = "src/test/java/pack/basket.csv";
        try (CSVReader reader = new CSVReader(new FileReader(fileName))) {

            //this line reads the csv file and puts it into a list, helped me easily to print
            List<String[]> fruitList = reader.readAll();


            int totalNumberOfFruit = 0;
            Set<String> uniqueListOfFruits = new TreeSet<>();
            Set<String> over3Days = new TreeSet<>();
            String[] eachFruitArray;
            int eachTypeCount;

            //for reversing to descending order, I used the reverseOrder()
            Map<String, Integer> newMap = new TreeMap<>(Collections.reverseOrder());

            for (int i = 1; i < fruitList.size(); i++) {

                eachFruitArray = fruitList.get(i);
                String fruit = eachFruitArray[0];

                //I added the fruits to set to make sure they are all unique
                uniqueListOfFruits.add(fruit);

                eachTypeCount = Integer.parseInt(eachFruitArray[1]);
                totalNumberOfFruit += eachTypeCount;

                //if the fruit is over three days, I put it into the Set to prevent the duplicates
                if (Integer.parseInt(eachFruitArray[4]) > 3) over3Days.add(fruit);

                /*
                I combined the fruit counts in the map. if the item is not listed in the map, I added.
                if the item is already listed, then I sum the count of the same type of fruit.
                 */
                if (!newMap.containsKey(fruit)) newMap.put(fruit, eachTypeCount);
                else newMap.put(fruit, eachTypeCount + newMap.get(fruit));
            }

            System.out.println("The total Number of Fruits = \t" + totalNumberOfFruit);

            int totalTypesOfFruit = uniqueListOfFruits.size();
            System.out.println("The total Type of Fruits = \t" + totalTypesOfFruit + "\n");

            System.out.println("The Characteristic List of each fruit is as: ");
            fruitList.forEach(x -> System.out.println(Arrays.toString(x)));

            System.out.println("Fruits that is over three days = \t" + over3Days);
            System.out.println("Fruit list in descending order = \t" + newMap);
        }
    }
}

//todo what I get in the console is a :
    /*
        The total Number of Fruits = 	69
        The total Type of Fruits = 	6
        The Characteristic List of each fruit is as:
        [name, size, color, shape, days]
        [orange, 4, red, round, 2]
        [pineapple, 3, yellow, round, 5]
        [grape, 10, white, flat, 8]
        [pear, 5, white, round, 6]
        [pear, 5, white, round, 6]
        [apple, 20, red, flat, 1]
        [orange, 2, orange , round, 3]
        [grape, 10, green, round, 4]
        [grapefruit, 3, green, flat, 1]
        [apple, 5, red, round, 3]
        [apple, 2, red, round, 2]
        Fruits that is over three days = 	[grape, pear, pineapple]
        Fruit list in descending order = 	{pineapple=3, pear=10, orange=6, grapefruit=3, grape=20, apple=27}



     */