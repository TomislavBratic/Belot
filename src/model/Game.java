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

    public void dealCards() {
        // in belot, we are dealing first 6 cards, in which player can see, and other 2 which player can not see
        // after dealing cards, player to the right of dealer can call trump, and if not, call is being passed to another player, in non-clockwise order
        // if no one calls, dealer must call it
        // after selecting trump, players can see rest of the cards, and player right of the dealer first plays

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
        deck.printCards(deck.shuffledCards);

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
    public String whoIsShuffling() {
        return teams.stream()
                .flatMap(team -> team.getMembers().stream())
                .map(Player::getName)
                .findFirst()
                .orElse("No player found");
    }

    public void playGame(){
        System.out.println("Game is about to start!");

        System.out.println("Shuffling teams!");
        System.out.println("First player is:" + whoIsShuffling() + ". " + teams.getFirst().getName() + " is dealing cards!");
        dealCards();
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
