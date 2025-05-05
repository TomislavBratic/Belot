package model;
import model.Player;
import model.Deck;
import model.Team;
import enums.Color;
import model.factories.PlayerFactory;

import java.util.*;
import java.util.stream.Collectors;

public class Game {
    private final Deck deck;
    private final List<Team> teams;
    private Color trump;

    public Game(String... playerNames) {
        if (playerNames.length != 2 && playerNames.length != 4) {
            throw new IllegalArgumentException("Game requires 2 or 4 players");
        }
        deck = new Deck();
        List<Player> shuffledPlayers = CreateAndShufflePlayers(playerNames);
        this.teams = createTeams(shuffledPlayers);
    }

    public Deck getDeck() {
        return deck;
    }

    public List<Team> getTeams() {
        return teams;
    }

    public Map<String, List<String>> getTeamMembers() {
        return teams.stream()
                .collect(Collectors.toMap(
                        Team::getName,
                        team -> team.getMembers()
                                .stream()
                                .map(Player::getName)
                                .collect(Collectors.toList())
                ));

    }
    public List<Team> createTeams(List<Player> players) {
        if (players.size() == 2) {
            return List.of(
                    new Team("Team 1", players.get(0), players.get(1))
            );
        } else {
            return List.of(
                    new Team("Team 1", players.get(0), players.get(2)),
                    new Team("Team 2", players.get(1), players.get(3))
            );
        }
    }

    public void dealCards() {
        List<Player> players = getPlayers();
        for( Player player: players)
        {
            for(int i = 0; i < 6; i++)
            {   if(!this.deck.shuffledCards.isEmpty())
                player.addToHand(this.deck.shuffledCards.get(i));
            }
        }
        chooseTrump();
        deck.setTrumpCards(deck.shuffledCards, trump);

        for( Player player: players)
        {
            for(int i = 0; i < 2; i++)
            {   if(!this.deck.shuffledCards.isEmpty())
                player.addToHand(this.deck.shuffledCards.get(i));
            }
        }
    }

    public List<Player> CreateAndShufflePlayers(String... playerNames) {
        List<Player> shuffledPlayers = PlayerFactory.createPlayers(playerNames);
        Collections.shuffle(shuffledPlayers);
        return shuffledPlayers;
    }

    public String whoIsShuffling() {
        return teams.stream()
                .flatMap(team -> team.getMembers().stream())
                .map(Player::getName)
                .findFirst()
                .orElse("No player found");
    }

    public List<Player> getPlayers(){
        return teams.stream()
                .flatMap(team -> team.getMembers().stream())
                .toList();
    }

    public void chooseTrump(){
        List<Color> colors = new ArrayList<>(List.of(Color.values()));
        Collections.shuffle(colors);
        trump = colors.getFirst();
        System.out.println("Trump is:" + trump);
    }

    public void playRound(int roundNumber){
        List<Player> players = getPlayers();
        List <Card> Table = new ArrayList<>();
        Random random = new Random();
        Round round = new Round(roundNumber);

        for(Player player: players)
        {
            int index = random.nextInt(player.hand.size());
            Card playedCard = player.playCard(index);
            Table.add(playedCard);
        }
    }

    public void spinningCursor(String message, int cycles, int delayMs) {
        String[] spinner = {"|", "/", "-", "\\"};
        for (int i = 0; i < cycles; i++) {
            System.out.print("\r" + message + " " + spinner[i % spinner.length]);
            try {
                Thread.sleep(delayMs);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
        System.out.print("\r" + message + " Done!\n");
    }

    public void playGame(){
        int roundNumber = 1;
        System.out.println("Game is about to start!");
        spinningCursor("Shuffling teams ", 20, 200);

        System.out.println("First player is:" + whoIsShuffling() + ". " + teams.getFirst().getName() + " is dealing cards!");
        spinningCursor("dealing cards ", 20, 200);
        dealCards();
        playRound(roundNumber);
    }

    /*------------ Exercises ---------------*/

    //Get player names from list of teams
    public List<String> getPlayerNames(){
        return teams.stream()
                .flatMap( team ->team.getMembers().stream())
                .map(Player::getName)
                .collect(Collectors.toList());
    }
    //Find player by a name
    public Optional<Player> findPlayer(String name){
        return teams.stream()
                .flatMap(team -> team.getMembers().stream())
                .filter(player -> player.getName().equals(name))
                .findFirst();
    }
    // Count number of players
    public long countPlayers(){
        return teams.stream()
                .flatMap(team -> team.getMembers().stream())
                .count();
    }
}
