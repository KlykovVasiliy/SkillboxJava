public class Main {
    public static void main(String[] args) {
        String text = "I like to travel. Most of all the members of our family. I like to take" +
                " long walks in the country. Such walks are called hikes. If the want to see the" +
                " countryside, we have to spend part of our summer holidays on hikes. They are" +
                " useful for all the members of our family. We take our rucksacks, we don't" +
                " think about tickets, we don't hurry and we walk a lot. During such hikes we" +
                " see a lot of interesting places and sometimes we meet interesting people. I" +
                " like to travel by car. It is interesting too, because you can see many things" +
                " in a short time. When we go by car, we don't need to buy tickets too and we put" +
                " all the things we need into the car. We don't carry them.";

        text = text.replaceAll("[^A-Za-z'А-Яа-я]+", " ");
        String fragments[] = text.split("\\s");
        int fragmentsCount = fragments.length;

        for (int a = 0; a < fragmentsCount; a++) {
            System.out.println(fragments[a].toLowerCase());
        }
    }
}
