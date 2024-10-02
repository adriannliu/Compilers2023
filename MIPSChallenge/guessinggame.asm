# Prompts the user guess a number between 1 and 100 and keep guessing until they choose the correct number. Provides 
# qualitative feedback to let the user know if their guess is too high/low.
# @author Adrian Liu
# @version 5/14/24
.data
    start: 	.asciiz "Guess a number between 1 and 100.\n"
    enterguess: .asciiz "Make a guess: "
    lower: 	.asciiz "Guess lower.\n"
    higher: 	.asciiz "Guess higher.\n"
    win: 	.asciiz "You have guessed the answer correctly!"
.text 0x00400000
.globl main
main:

    li $v0, 4
    la $a0, start
    syscall

#generating random number
li $v0 42
li $a1 100
syscall
move $t0 $a0

    whileloop:
        # prompts user to enter a number
        la $a0, enterguess
        syscall
        li $v0, 5
        syscall
        move $t1, $v0

        # checks user's guess and jumps to the proper loop to process their response
        li $v0, 4
        beq $t0, $t1, endloop
        blt $t0, $t1, lower
        bgt $t0, $t1, higher

    higher:
	#tells the user to guess higher
        la $a0, higher
        syscall
        j whileloop

    lower:
	#tells the user to guess lower
        la $a0, lower    
        syscall
        j whileloop
        
    endloop:
	#prints that the user was correct
        la $a0, win
        syscall

	#normal termination
        li $v0, 10
        syscall

