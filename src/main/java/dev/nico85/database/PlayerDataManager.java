package dev.nico85.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.bukkit.entity.Player;

public class PlayerDataManager {
    private SQLiteManager sqliteManager;

    public PlayerDataManager(SQLiteManager sqliteManager) {
        this.sqliteManager = sqliteManager;
    }

    public void savePlayerData(Player player, int age, String gender) {
        Connection connection = sqliteManager.getConnection();
        try {
            String query = "INSERT INTO player_data (player_name, age, gender) VALUES (?, ?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, player.getName());
            preparedStatement.setInt(2, age);
            preparedStatement.setString(3, gender);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public int getPlayerAge(Player player) {
        Connection connection = sqliteManager.getConnection();
        try {
            String query = "SELECT age FROM player_data WHERE player_name = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, player.getName());
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return resultSet.getInt("age");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1;
    }

    public String getPlayerSex(Player player) {
        Connection connection = sqliteManager.getConnection();
        try {
            String query = "SELECT gender FROM player_data WHERE player_name = ?";
            PreparedStatement genderget = connection.prepareStatement(query);
            genderget.setString(1, player.getName());
            ResultSet resultSet = genderget.executeQuery();
            if (resultSet.next()) {
                return resultSet.getString("gender");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return "nero";
    }

    public boolean isPlayerRegistered(Player player) {
        Connection connection = sqliteManager.getConnection();
        try {
            String query = "SELECT player_name FROM player_data WHERE player_name = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, player.getName());
            ResultSet resultSet = preparedStatement.executeQuery();
            return resultSet.next();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }


}

