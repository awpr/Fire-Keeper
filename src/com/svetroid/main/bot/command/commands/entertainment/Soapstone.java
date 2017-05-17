package com.svetroid.main.bot.command.commands.entertainment;

import com.svetroid.main.Main;
import com.svetroid.main.bot.command.Command;
import com.svetroid.main.event.Input;
import com.svetroid.main.event.Output;

import java.security.SecureRandom;

public class Soapstone extends Command {

    public Soapstone() {
        settings.setNameAndAliases("soapstone");
        settings.setHelp(Main.bot.getPrefix() + "soapstone");
        settings.setDescription("Generates a random soapstone message.");
    }

    @Override
    protected void execute(Input input, Output output) {
        StringBuilder builder = new StringBuilder(":soapstone: ");
        select(templates, random).generate(builder, select(fillers, random));
        String connective = select(connectives, random);
        if (connective != null) {
            builder.append(connective);
            select(templates, random).generate(builder, select(fillers, random));
        }
        if (random.nextInt(emojiInvProbability) == 0) {
            builder.append(" :").append(select(emoji, random)).append(':');
        }
        output.sendMessage(builder.toString());
    }

    private static <T> T select(T[] arr, SecureRandom random) {
        return arr[random.nextInt(arr.length)];
    }

    // A Template represents one message-component with a hole for a filler word.
    private static class Template {
        public Template(String prefix, String suffix) {
            this.prefix = prefix;
            this.suffix = suffix;
        }

        public void generate(StringBuilder b, String filler) {
            b.append(prefix).append(filler).append(suffix);
        }

        private String prefix;
        private String suffix;
    }

    private SecureRandom random = new SecureRandom();

    // Connectives is a list of nullable strings that is sampled randomly to
    // choose the message format: if null is chosen, only one message-component
    // is generated.
    private static String[] connectives = {
            null, " and then ", " but ", " therefore ", " in short ", " or ", " only ", " by the way ", " so to speak ", " all the more ", ", "};
    private static Template[] templates = {
            new Template("", " ahead"),
            new Template("No ", " ahead"),
            new Template("", " required ahead"),
            new Template("try ", ""),
            new Template("Could this be a ", "?"),
            new Template("If only I had a ", "..."),
            new Template("visions of ", "..."),
            new Template("Time for ", ""),
            new Template("", ""),
            new Template("", "!"),
            new Template("", "?"),
            new Template("", "..."),
            new Template("Huh.  It's a ", "..."),
            new Template("praise the ", "!"),
            new Template("Let there be ", ""),
            new Template("Ahh, ", "..."),
            new Template("try ", ""),
    };
    static int emojiInvProbability = 20;  // 1/N messages will get emoji.
    private static String[] emoji = {
            "Solaire", "pRaisun", "mmmmh", "Estus", "AshenEstus", "Neato", "Siegbrau", "Dad", "GitGud", "dinking",
            "very_good_carving"};
    private static String[] fillers = {
            // Creatures
            "enemy", "monster", "mob enemy", "tough enemy", "critical foe", "Hollow", "pilgrim", "prisoner",
            "monstrosity", "skeleton", "ghost", "beast", "lizard", "bug", "grub", "crab", "dwarf", "giant", "demon",
            "dragon", "knight", "sellsword", "warrior", "herald", "bandit", "assassin", "sorceror", "pyromancer",
            "cleric", "deprived", "sniper", "duo", "trio", "you", "you bastard", "good fellow", "saint", "wretch",
            "charmer", "poor soul", "oddball", "nimble one", "laggard", "moneybags", "beggar", "miscreant", "liar",
            "fatty", "beanpole", "youth", "elder", "old codger", "old dear", "merchant", "artisan", "master", "sage",
            "champion", "Lord of Cinder", "king", "queen", "prince", "princess", "angel", "god", "friend", "ally",
            "spouse", "covenantor", "Phantom", "Dark Spirit",

            // Objects
            "bonfire", "ember", "fog wall", "lever", "contraption", "key", "trap", "torch", "door", "treasure", "chest",
            "something", "quite something", "rubbish", "filth", "weapon", "shield", "projectile", "armor", "item",
            "ring", "ore", "coal", "transposing kiln", "scroll", "umbral ash", "throne", "rite", "coffin", "cinder",
            "ash", "moon", "eye", "brew", "soup", "message", "bloodstain", "illusion",

            // Techniques
            "close-ranged battle", "ranged battle", "eliminating one at a time", "luring it out", "beating to a pulp",
            "ambush", "pincer attack", "hitting them in one swoop", "dual-wielding", "stealth", "mimicry", "fleeing",
            "charging", "jumping off", "dashing through", "circling around", "trapping inside", "rescue", "Skill",
            "sorcery", "pyromancy", "miracles", "pure luck", "prudence", "brief respite", "play dead",

            // Actions
            "jog", "dash", "rolling", "backstepping", "jumping", "attacking", "jump attack", "dash attack",
            "counter attack", "stabbing in the back", "guard stun & stab", "plunging attack", "sweeping attack",
            "shield breaking", "blocking", "parrying", "locking-on", "no lock-on", "two-handing", "gesture", "control",
            "destroy",

            // Geography
            "boulder", "lava", "poison gas", "enemy horde", "forest", "swamp", "cave", "shortcut", "detour",
            "hidden path", "secret passage", "dead end", "labyrinth", "hole", "bright spot", "dark spot", "open area",
            "tight spot", "safe zone", "danger zone", "sniper spot", "hiding place", "illusory wall", "ladder", "lift",
            "gorgeous view", "looking away", "overconfidence", "slip-up", "oversight", "fatigue", "bad luck",
            "inattention", "loss of stamina", "chance encounter", "planned encounter",

            // Orientation
            "front", "back", "left", "right", "up", "down", "below", "above", "behind",

            // Body parts
            "head", "neck", "stomach", "back", "arm", "finger", "leg", "rear", "tail", "wings", "anywhere", "tongue",
            "right arm", "left arm", "thumb", "indexfinger", "longfinger", "ringfinger", "smallfinger", "right leg",
            "left leg", "right side", "left side", "pincer", "wheel", "core", "mount",

            // Attribute
            "regular", "strike", "thrush", "slash", "magic", "crystal", "fire", "chaos", "lightning", "blessing", "dark",
            "critical hits", "bleeding", "poison", "toxic", "frost", "curse", "equipment breakage",

            // Concepts
            "chance", "quagmire", "hint", "secret", "sleeptalk", "happiness", "misfortune", "life", "death", "demise",
            "joy", "fury", "agony", "sadness", "tears", "loyalty", "betrayal", "hope", "despair", "fear",
            "losing sanity", "victory", "defeat", "sacrifice", "light", "dark", "bravery", "confidence", "vigor",
            "revenge", "resignation", "overwhelming", "regret", "pointless", "man", "woman", "friendship", "love",
            "recklessness", "composure", "guts", "comfort", "silence", "deep", "dregs",

            // Musings
            "good luck", "fine work", "I did it!", "I've failed...", "here!", "not here!", "I can't take this...",
            "lonely...", "don't you dare!", "do it!", "look carefully", "listen carefully", "think carefully",
            "this place again?", "now the real fight begins", "you don't deserve this", "keep moving", "pull back",
            "give it up", "don't give up", "help me...", "impossible...", "bloody expensive...",
            "let me out of here...", "stay calm", "like a dream...", "seems familiar...", "are you ready?",
            "it'll happen to you too", "praise the Sun!", "may the flames guide thee"};
}
