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
            Set<String> characteristics = new TreeSet<>();
            Map<String, Integer> over3map = new TreeMap<>();
            Map<Integer, String> fruitSort = new TreeMap<>(Collections.reverseOrder());
            Map<String, Integer> newMap = new TreeMap<>();

            String[] eachFruitArray;
            int eachTypeCount;

            for (int i = 1; i < fruitList.size(); i++) {

                eachFruitArray = fruitList.get(i);
                String fruit = eachFruitArray[0];

                //I added the fruits to set to make sure they are all unique
                uniqueListOfFruits.add(fruit);

                characteristics.add(eachFruitArray[1]+" "+eachFruitArray[0]+": "+eachFruitArray[2]+", "+eachFruitArray[3]);
                eachTypeCount = Integer.parseInt(eachFruitArray[1]);
                totalNumberOfFruit += eachTypeCount;

                //if the fruit is over three days, I put it into the Set to prevent the duplicates
                if (Integer.parseInt(eachFruitArray[4]) > 3) {
                    over3map.put(fruit, eachTypeCount);
                }

                /*
                I combined the fruit counts in the map. if the item is not listed in the map, I added.
                if the item is already listed, then I sum the count of the same type of fruit.
                 */
                if (!newMap.containsKey(fruit)) newMap.put(fruit, eachTypeCount);
                else newMap.put(fruit, eachTypeCount + newMap.get(fruit));
            }

            System.out.println("Total number of fruit = \t" + totalNumberOfFruit);

            int totalTypesOfFruit = uniqueListOfFruits.size();
            System.out.println("Types of fruit = \t" + totalTypesOfFruit + "\n");

            System.out.println("The number of each type of fruit in descending order = \t");
            for (Map.Entry<String, Integer> entry : newMap.entrySet())
                fruitSort.put(entry.getValue(), entry.getKey());
            fruitSort.forEach((k, v) -> System.out.println(v + ": " + k));

            System.out.println("The characteristics (size, color, shape, etc.) of each fruit by type = ");
            characteristics.forEach(x -> System.out.println(x));

            System.out.print("Fruits that has been in the basket for over 3 days = ");
            over3map.forEach((k, v) -> System.out.print(v + " " + k + ", "));
            System.out.print("are over three days.\n\n");
        }
    }
}

//todo what it going to print is :
/*
Total number of fruit = 	69
Types of fruit = 	6

The number of each type of fruit in descending order =
apple: 27
grape: 20
pear: 10
orange: 6
pineapple: 3
The characteristics (size, color, shape, etc.) of each fruit by type =
10 grape: green, round
10 grape: white, flat
2 apple: red, round
2 orange: orange , round
20 apple: red, flat
3 grapefruit: green, flat
3 pineapple: yellow, round
4 orange: red, round
5 apple: red, round
5 pear: white, round
Fruits that has been in the basket for over 3 days = 10 grape, 5 pear, 3 pineapple, are over three days.
 */