import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;

public class GUIInput implements Input{
    private JFrame frame;

    public GUIInput(JFrame frame) {
        this.frame = frame;
    }
    @Override
    public int getNumPlayers() {
        while (true) {
            String input = JOptionPane.showInputDialog(frame, "Enter the number of players (2-8):");
            try {
                int num = Integer.parseInt(input);
                if (num >= 2 && num <= 8) {
                    return num;
                }
            } catch (NumberFormatException ignored) {}
            JOptionPane.showMessageDialog(frame, "Invalid input. Enter a number between 2 and 8.");
        }
    }

    @Override
    public String getPlayerName(int playerNum) {
        return JOptionPane.showInputDialog(frame, "Enter name for player " + playerNum + ":");
    }

    @Override
    public String selectAction(String[] actions) {
        return selectOption("action", actions);
    }

    @Override
    public Room selectRoom(String[] rooms) {
        String roomName = selectOption("room", rooms);
        return Room.getRoom(roomName);
    }

    @Override
    public Role selectRole(Role[] roles) {
        String[] roleNames = Arrays.stream(roles)
                .map(Role::getName)
                .toArray(String[]::new);
        String selectedRoleName = selectOption("role", roleNames);
        for (Role role : roles) {
            if (role.getName().equals(selectedRoleName)) {
                return role;
            }
        }
        return null;
    }

    @Override
    public int chooseRank(int currRank, int maxRank) {// add currency
        // button grid with unavailable upgrades grayed out or gone
        return 0;
    }

    private String selectOption(String toSelect, String[] options) {
        // commented these lines out so it doesn't break, for now there's a bug
//        JLayeredPane interfacePane = getInterfacePane();
//        clear(interfacePane);
        JLayeredPane interfacePane = new JLayeredPane();
        interfacePane.setPreferredSize(new Dimension(frame.getWidth() / 5, frame.getHeight() / 2));
        interfacePane.setBorder(BorderFactory.createLineBorder(new Color(0, 0, 0), 5));
        interfacePane.setLayout(new FlowLayout());
        frame.add(interfacePane);
        frame.pack();

        JLabel prompt = new JLabel("Select a(n) " + toSelect + ":");
        interfacePane.add(prompt);

        // idk why I had to do it this way, it's really dumb but it works ig
        final String[] selectedOption = new String[1];



        for (String option: options) {
            JButton button = new JButton(option);
            button.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    selectedOption[0] = option;
                }
            });

            interfacePane.add(button);
        }
        frame.pack();

        // wait for a button to be pressed
        while (selectedOption[0] == null) {
            try {
                Thread.sleep(1);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        frame.remove(interfacePane);
        frame.pack();

        return selectedOption[0];
    }

    private void clear(JLayeredPane pane) {
        while (pane.getComponentCount() > 0) {
            pane.remove(0);
        }
    }

    private JLayeredPane getInterfacePane() {
        System.out.println(frame.getContentPane());
        JLayeredPane sidePane = (JLayeredPane) frame.getContentPane().getComponent(1);//frame.getComponents()[1];//getPane(frame, "Side Pane");
        assert sidePane != null;
        System.out.println(sidePane.getName());
        return getPane(sidePane, "Interface");
    }

    private JLayeredPane getPane(JFrame frame, String paneName) {
        for (Component comp: frame.getComponents()) {
            if (comp.getName() == null) continue;

            if (comp.getName().equals(paneName))
                return (JLayeredPane) comp;
        }

        return null;
    }

    private JLayeredPane getPane(JLayeredPane pane, String paneName) {
        for (Component comp: pane.getComponents()) {
            if (comp.getName() == null) continue;

            if (comp.getName().equals(paneName))
                return (JLayeredPane) comp;
        }

        return null;
    }
}
