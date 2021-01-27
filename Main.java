package com.company;

import java.util.*;
import java.util.function.Consumer;

public class Main {

    public static void main(String[] args) {
        Callback callback = new Callback();
        System.out.println("Введите массив: ");
        try {
            int[] arr = readArray();
            for (int j : arr) {
                storage.add(j);
            }
            tmp = new int[arr.length];

            solve(0, callback);

            System.out.println("Перестановки: ");
            for (int[] res : callback.permutations) {
                for (int re : res) {
                    System.out.printf("%d ", re);
                }
                System.out.println();
            }
        } catch (Exception ex) {
            System.out.println("Ошибка ввода");
            System.exit(1);
        }
    }

    static int[] readArray() {
        Scanner in = new Scanner(System.in);
        String[] parts = in.nextLine().split(" ");
        int[] arr = new int[parts.length];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = Integer.parseInt(parts[i]);
        }
        return arr;
    }

    static LinkedList<Integer> storage = new LinkedList<>();
    static int[] tmp;

    static void solve(int n, Consumer<int[]> callback) {
        if (n == tmp.length) {
            callback.accept(tmp);
        } else {
            for (int i = 0; i < storage.size(); i++) {
                Integer val = storage.pollFirst();
                tmp[n] = val;
                solve(n + 1, callback);
                storage.add(val);
            }
        }
    }

    static class Callback implements Consumer<int[]> {
        ArrayList<int[]> permutations = new ArrayList<>();

        @Override
        public void accept(int[] ints) {
            permutations.add(copyArr(ints));
        }


        int[] copyArr(int[] ints) {
            int[] res = new int[ints.length];
            for (int i = 0; i < ints.length; i++) {
                res[i] = ints[i];
            }
            return res;
        }
    }
}
