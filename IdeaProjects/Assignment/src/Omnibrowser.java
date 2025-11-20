package com.myBrowser;
import java.util.*;

public class Omnibrowser {

    // Represents an entry in global history with optional note (e.g., "(via Back)")
    static class HistoryEntry {
        String url;
        String note; // may be empty or null

        HistoryEntry(String url, String note) {
            this.url = url;
            this.note = (note == null) ? "" : note;
        }
    }

    // Tab class: manages its own back/forward stacks and current page
    static class Tab {
        final int id; // internal id (monotonic)
        Deque<String> backStack;
        Deque<String> forwardStack;
        String current;

        Tab(int id, String initialUrl) {
            this.id = id;
            this.backStack = new ArrayDeque<>();
            this.forwardStack = new ArrayDeque<>();
            this.current = initialUrl;
        }

        void visit(String url) {
            if (current != null) backStack.push(current);
            current = url;
            forwardStack.clear(); // New path rule: visiting new URL clears forward history
        }

        boolean canBack() {
            return !backStack.isEmpty();
        }

        boolean canForward() {
            return !forwardStack.isEmpty();
        }

        // returns the new current URL, or null if cannot go back
        String goBack() {
            if (!canBack()) return null;
            forwardStack.push(current);
            current = backStack.pop();
            return current;
        }

        // returns the new current URL, or null if cannot go forward
        String goForward() {
            if (!canForward()) return null;
            backStack.push(current);
            current = forwardStack.pop();
            return current;
        }
    }

    // Browser: manages tabs and global history
    static class Browser {
        LinkedList<Tab> tabs;     // container for tabs
        int currentTabIndex;      // 0-based index of active tab in 'tabs'; -1 if none
        int nextTabId;            // monotonic id for tabs
        Deque<HistoryEntry> globalHistory; // most recent at front; capacity 10

        Browser() {
            this.tabs = new LinkedList<>();
            this.currentTabIndex = -1;
            this.nextTabId = 1;
            this.globalHistory = new ArrayDeque<>();
        }

        // helper to add to global history (most recent first). Keeps max 10.
        private void addToGlobalHistory(String url, String note) {
            globalHistory.addFirst(new HistoryEntry(url, note));
            if (globalHistory.size() > 10) {
                globalHistory.removeLast();
            }
        }

        void cmdNew(String url) {
            Tab t = new Tab(nextTabId++, url);
            tabs.add(t);
            currentTabIndex = tabs.size() - 1;
            addToGlobalHistory(url, null);
            System.out.printf("[Success] Tab %d created. Loading %s...%n", tabs.size(), url);
        }

        void cmdVisit(String url) {
            Tab cur = getCurrentTab();
            if (cur == null) {
                System.out.println("No tab open. Use NEW [url] to open a tab.");
                return;
            }
            String prev = cur.current;
            cur.visit(url);
            System.out.printf("[Nav] Tab %d: %s -> %s%n", currentTabIndex + 1,
                    prev == null ? "blank" : prev, url);
            addToGlobalHistory(url, null);
        }

        void cmdBack() {
            Tab cur = getCurrentTab();
            if (cur == null) {
                System.out.println("No tab open.");
                return;
            }
            String from = cur.current;
            String to = cur.goBack();
            if (to == null) {
                System.out.println("No history to go back to.");
            } else {
                System.out.printf("[Back] Tab %d: %s -> %s%n", currentTabIndex + 1, from, to);
                addToGlobalHistory(to, "(via Back)");
            }
        }

        void cmdFwd() {
            Tab cur = getCurrentTab();
            if (cur == null) {
                System.out.println("No tab open.");
                return;
            }
            String from = cur.current;
            String to = cur.goForward();
            if (to == null) {
                System.out.println("No forward history.");
            } else {
                System.out.printf("[Forward] Tab %d: %s -> %s%n", currentTabIndex + 1, from, to);
                addToGlobalHistory(to, "(via Forward)");
            }
        }

        void cmdTab(String argIndex) {
            if (tabs.isEmpty()) {
                System.out.println("No tabs open.");
                return;
            }
            int idx;
            try {
                idx = Integer.parseInt(argIndex);
            } catch (NumberFormatException e) {
                System.out.println("Invalid Tab ID.");
                return;
            }
            if (idx < 1 || idx > tabs.size()) {
                System.out.println("Invalid Tab ID.");
                return;
            }
            currentTabIndex = idx - 1;
            Tab cur = getCurrentTab();
            System.out.printf("[Switch] Now in Tab %d. Current: %s%n", idx,
                    (cur.current == null ? "blank" : cur.current));
        }

        void cmdClose() {
            if (tabs.isEmpty() || currentTabIndex < 0) {
                System.out.println("No tab open to close.");
                return;
            }
            int closingIndex = currentTabIndex;
            Tab closingTab = tabs.remove(closingIndex);
            System.out.printf("[Close] Closed Tab %d.%n", closingIndex + 1);

            // choose the next current tab: previous (closingIndex-1) else same index (which now points to next)
            if (tabs.isEmpty()) {
                currentTabIndex = -1;
                System.out.println("No tabs remaining.");
            } else {
                if (closingIndex - 1 >= 0) {
                    currentTabIndex = closingIndex - 1;
                } else {
                    currentTabIndex = Math.min(closingIndex, tabs.size() - 1);
                }
                Tab cur = getCurrentTab();
                System.out.printf("[Switch] Now in Tab %d. Current: %s%n", currentTabIndex + 1,
                        (cur.current == null ? "blank" : cur.current));
            }
        }

        void cmdHistory() {
            System.out.println("Global History:");
            if (globalHistory.isEmpty()) {
                System.out.println("(empty)");
                return;
            }
            int i = 1;
            for (HistoryEntry he : globalHistory) {
                if (he.note == null || he.note.isEmpty()) {
                    System.out.printf("%d. %s%n", i++, he.url);
                } else {
                    System.out.printf("%d. %s %s%n", i++, he.url, he.note);
                }
            }
        }

        Tab getCurrentTab() {
            if (currentTabIndex < 0 || currentTabIndex >= tabs.size()) return null;
            return tabs.get(currentTabIndex);
        }

        // small helper to show prompt status (optional)
        void printPrompt() {
            System.out.print("OmniBrowser > ");
        }
    }

    // Utility to parse command and an optional argument (the rest of the line after command)
    private static String[] splitCommand(String line) {
        String trimmed = line.trim();
        if (trimmed.isEmpty()) return new String[] {"", ""};
        String[] parts = trimmed.split("\\s+", 2);
        String cmd = parts[0].toUpperCase(Locale.ROOT);
        String arg = (parts.length > 1) ? parts[1].trim() : "";
        // Remove surrounding brackets and parentheses if user types like [www.google.com]
        if (!arg.isEmpty()) {
            arg = stripBrackets(arg);
        }
        return new String[] {cmd, arg};
    }

    private static String stripBrackets(String s) {
        s = s.trim();
        if ((s.startsWith("[") && s.endsWith("]")) || (s.startsWith("(") && s.endsWith(")"))) {
            s = s.substring(1, s.length() - 1).trim();
        }
        return s;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Browser browser = new Browser();

        System.out.println("Welcome to OmniBrowser v1.0");
        System.out.println("Type 'EXIT' to quit.\n");

        while (true) {
            browser.printPrompt();
            if (!sc.hasNextLine()) break;
            String line = sc.nextLine().trim();
            if (line.isEmpty()) continue;

            String[] parsed = splitCommand(line);
            String cmd = parsed[0];
            String arg = parsed[1];

            switch (cmd) {
                case "EXIT":
                    System.out.println("Closing OmniBrowser... Goodbye.");
                    sc.close();
                    return;

                case "NEW":
                    if (arg.isEmpty()) {
                        System.out.println("Usage: NEW [url]");
                    } else {
                        browser.cmdNew(arg);
                    }
                    break;

                case "VISIT":
                    if (arg.isEmpty()) {
                        System.out.println("Usage: VISIT [url]");
                    } else {
                        browser.cmdVisit(arg);
                    }
                    break;

                case "BACK":
                    browser.cmdBack();
                    break;

                case "FWD":
                case "FORWARD": // accept FORWARD as alias
                    browser.cmdFwd();
                    break;

                case "TAB":
                    if (arg.isEmpty()) {
                        System.out.println("Usage: TAB [index]");
                    } else {
                        browser.cmdTab(arg);
                    }
                    break;

                case "CLOSE":
                    browser.cmdClose();
                    break;

                case "HISTORY":
                    browser.cmdHistory();
                    break;

                default:
                    System.out.println("Unknown command. Valid commands: NEW, VISIT, BACK, FWD, TAB, CLOSE, HISTORY, EXIT");
            }
        }
    }
}