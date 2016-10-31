package ru.ganev.xo.view.menu;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import static java.lang.System.out;

public class Menu {

    private List<MenuLine> entries;
    private boolean isExit = false;

    public Menu(List<MenuLine> entries) {
        this.entries = entries;
        entries.add(new MenuLine(entries.size() + 1 + " - Exit") {
            @Override
            public void execute() {
                isExit = true;
            }
        });
    }

    public static MenuBuilder builder() {
        return new MenuBuilder();
    }

    public void execute() {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        while (!isExit) {
            printMenu();
            String line;
            try {
                line = reader.readLine();
                int choice = Integer.parseInt(line);
                MenuLine entry = entries.get(choice - 1);
                entry.execute();
            } catch (IOException e) {
                throw new RuntimeException(e);
            } catch (IndexOutOfBoundsException e) {
                out.println("Please enter correct menu code\n");
            }
        }
    }

    private void printMenu() {
        entries.stream()
                .map(MenuLine::getCaption)
                .forEach(out::println);
        out.println();
    }


    public static final class MenuBuilder {

        private List<MenuLine> entries = new ArrayList<>();
        private boolean isExit = false;

        private MenuBuilder() {
        }

        public MenuBuilder entry(MenuLine entry) {
            this.entries.add(entry);
            return this;
        }

        public MenuBuilder isExit(boolean isExit) {
            this.isExit = isExit;
            return this;
        }

        public Menu build() {
            Menu menu = new Menu(entries);
            menu.isExit = this.isExit;
            return menu;
        }
    }
}