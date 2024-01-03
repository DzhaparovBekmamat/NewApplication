package com.chihuahua.pokereducation.ui.fragments.choices.quiz.model

import com.chihuahua.pokereducation.ui.fragments.choices.quiz.model.QuizModel
import com.template.newapplication.R

/**
 * Author: Dzhaparov Bekmamat
 */
object Constants {
    fun getQuestion(): ArrayList<QuizModel> {
        val questionList = ArrayList<QuizModel>()/*
         val que1 = Question(
            1,
            "Who is the all-time leading goal scorer in FIFA World Cup history?",
            R.drawable.a,
            "Lionel Messi",
            "Cristiano Ronaldo",
            "Pele",
            "Neymar Jr.",
            3
        )
         */
        val question1 = QuizModel(
            1,
            "LEVEL 1",
            "You're dealt a pair of Jacks, a decent hand but not unbeatable. Your opponent seems confident, odds aggressively. Do you risk it all and go all-in, or fold and live to fight another hand?",
            R.drawable.s1,
            "Decide whether to go all-in or fold \u2028with a pair of Jacks",
            "Go All-In",
            "Fold",
            1
        )
        val question2 = QuizModel(
            2,
            "LEVEL 2",
            "The river card is revealed, completing the community cards. You have a weak hand, but your opponent looks uncertain. Do you try a bold bluff, hoping to make them fold, or play it safe and check, hoping your opponent isn't holding a stronger hand?",
            R.drawable.s21,
            "Decide whether to bluff on the river with \u2028a weak hand",
            "Bluff",
            "Check",
            1
        )
        val question3 = QuizModel(
            3,
            "LEVEL 3",
            "The turn card is a suit away from completing your flush. Your opponent odds heavily, indicating strength. Do you make the risky call, hoping for the flush on the river, or fold and preserve your chips for a better opportunity?",
            R.drawable.s2,
            "Decide whether to call a large odd with a flush draw on the turn",
            "Call",
            "Fold",
            1
        )
        val question4 = QuizModel(
            4,
            "LEVEL 4",
            "You're holding a potential straight, but it's not there yet. The board looks unassuming. Do you make a significant odds, trying to scare your opponent away and bluff them out, or do you check, hoping to see the next card \u2028for free?",
            R.drawable.s3,
            "Decide whether to make a big odd on the turn \u2028with a potential straight",
            "Odd Big",
            "Check",
            1
        )
        val question5 = QuizModel(
            5,
            "LEVEL 5",
            "You've got a strong hand, but your opponent seems aggressive, possibly bluffing. Do you trap them by just calling, hoping they'll overcommit, or do you fold, fearing they might actually have a better hand?",
            R.drawable.s51,
            "Decide whether to trap your opponent \u2028with a strong hand or fold to a suspected bluff",
            "Trap",
            "Fold",
            1
        )
        val question6 = QuizModel(
            6,
            "LEVEL 6",
            "You find yourself with a pair of Aces before the flop - a powerful starting hand. Do you go all-in, trying to intimidate opponents and win big, or do you call, hoping to entice more players into the pot?",
            R.drawable.s4,
            "Decide whether to go all-in \u2028with a pair of Aces pre-flop",
            "Go all in",
            "Call",
            1
        )
        val question7 = QuizModel(
            7,
            "LEVEL 7",
            "You're holding an open-ended straight draw, giving you several potential winning hands. Do you odds confidently, semi-bluffing and possibly forcing your opponent to fold, or do you check, risking your opponent catching \u2028a better hand on the next card?",
            R.drawable.s5,
            "Decide whether to semi-bluff with \u2028an open-ended straight draw",
            "Semi-Bluff",
            "Check",
            1
        )
        val question8 = QuizModel(
            8,
            "LEVEL 8",
            "You have a low pair, not a strong hand but not hopeless either. Your opponent makes a hefty odds, indicating confidence. Do you make the desperate call, hoping they're bluffing, or do you fold, avoiding a potentially disastrous loss?",
            R.drawable.s61,
            "Decide whether to call a large odd with a low pair",
            "Call",
            "Fold",
            1
        )
        questionList.add(question1)
        questionList.add(question2)
        questionList.add(question3)
        questionList.add(question4)
        questionList.add(question5)
        questionList.add(question6)
        questionList.add(question7)
        questionList.add(question8)
        return questionList
    }
}