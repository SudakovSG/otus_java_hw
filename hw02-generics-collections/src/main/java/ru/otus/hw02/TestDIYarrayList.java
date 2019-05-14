package ru.otus.hw02;

import java.util.*;

public class TestDIYarrayList {

    public static void main(String... args) {

        List<String> diYarrayList = new DIYarrayList<>();

        //Collections.addAll(Collection<? super T> c, T... elements);
        Collections.addAll(diYarrayList, "diy first string", "diy second string", "diy third string", "diy fourth string");
        System.out.println("new: " + Arrays.toString(diYarrayList.toArray()));

        for (String entry: generateRandomWords(30)) {
            diYarrayList.add(entry);
        }
        System.out.println("with random: " + Arrays.toString(diYarrayList.toArray()));

        List<String> arrayList = new ArrayList();
        arrayList.add("first string");
        arrayList.add("second string");
        for (String entry: generateRandomWords(20)) {
            arrayList.add(entry.toUpperCase());
        }


        //Collections.static <T> void copy(List<? super T> dest, List<? extends T> src);
        Collections.copy(diYarrayList, arrayList);
        System.out.println("after copy: " + Arrays.toString(diYarrayList.toArray()));

        //Collections.static <T> void sort(List<T> list, Comparator<? super T> c);
        Collections.sort(diYarrayList, new ReverseStringComparator());
        //Collections.sort(diYarrayList, String.CASE_INSENSITIVE_ORDER);
        System.out.println("after sort: " + Arrays.toString(diYarrayList.toArray()));
    }

    public static String[] generateRandomWords(int numberOfWords)
    {
        String[] randomStrings = new String[numberOfWords];
        Random random = new Random();
        for(int i = 0; i < numberOfWords; i++)
        {
            char[] word = new char[random.nextInt(8)+3];
            for(int j = 0; j < word.length; j++)
            {
                word[j] = (char)('a' + random.nextInt(26));
            }
            randomStrings[i] = new String(word);
        }
        return randomStrings;
    }

    private static class ReverseStringComparator implements Comparator<String> {

        @Override
        public int compare(String s1, String s2) {
            StringBuilder sbs1 = new StringBuilder(s1);
            StringBuilder sbs2 = new StringBuilder(s2);
            sbs1 = sbs1.reverse();
            sbs2 = sbs2.reverse();

            return sbs1.toString().compareTo(sbs2.toString());
        }
    }

}