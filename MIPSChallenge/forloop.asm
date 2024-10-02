# Prompts the user to enter 3 numbers: low, high, and step, then prints the numbers between
# the low and high, incremementing by step
# @author Adrian Liu
# @version 5/14/24
.data 
	msg1: .asciiz "Enter a lower bound: "
	msg2: .asciiz "Enter a higher bound: "
	msg3: .asciiz "Enter a step: "
	space: 	 .asciiz " "
.text 0x00400000
.globl main

main:
	# asks for lower number and stores in register t0
	li $v0 4
	la $a0 msg1
	syscall
	
	li $v0 5
	syscall
	move $t0 $v0

	# asks for higher number and stores in register t1
	li $v0 4
	la $a0 msg2
	syscall
	
	li $v0 5
	syscall
	move $t1 $v0
	
	# ask for step and stores in register t2
	li $v0 4
	la $a0 msg3
	syscall
	
	li $v0 5
	syscall
	move $t2 $v0
	
	adding:
		# ends loop if lower > upper
		bgt $t0 $t1 end 
		li $v0 1 		
		# prints current number followed by a space
		move $a0 $t0
		syscall
		
		li $v0 4
		la $a0 space
		syscall
		
		addu $t0 $t0 $t2 	# increment t0 (lower pointer) by step
		j adding
	end: 
		# normal termination
		li $v0 10
		syscall

