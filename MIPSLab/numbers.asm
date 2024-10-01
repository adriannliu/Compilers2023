# Prompts the user to enter 10 numbers and stores them in an array. Prints the sum,
# average, max, and min of these 10 numbers.
# @author Adrian Liu
# @version 5/14/24

.data 
	msg:	.asciiz	"Input a number: "
		.align 	2
	arr:	.space	40
	sum:	.asciiz "\nThe sum of the 10 numbers is: "
	avg:	.asciiz "\nThe average of the 10 numbers is: "
	max: 	.asciiz "\nThe max of the 10 numbers is: "
	min:	.asciiz "\nThe min of the 10 numbers is: "

.text 0x00400000
.globl main

main:
	# initialize index variable to 0
	li $t0 0

# gather all 10 numbers in an array arr
for:
	# loop sentinel, runs 10 times
	beq $t0 40 sumInit

	# prompts user for a number
	li $v0 4
	la $a0 msg
	syscall
	
	# store input in $t1
	li $v0 5
	syscall
	move $t1 $v0
	
	# store input in arr 
	sw $t1 arr($t0)
	addi $t0 $t0 4
	
	# repeat
	j for
	
# initialize variables to help find sum
sumInit: 
	# index of array
	li $t0 36
	# running sum
	li $t2 0 
	
findSum:
	blt $t0 0 printSum #sentinel
	lw $t1 arr($t0)
	addu $t2 $t2 $t1
	subu $t0 $t0 4
	j findSum
	
printSum:
	li $v0 4
	la $a0 sum
	syscall
	li $v0 1
	move $a0 $t2
	syscall

# print average which is thankfully really easy to find
printAvg:
	li $v0 4
	la $a0 avg
	syscall
	
	# divide sum by 10 to find average
	divu $a0 $t2 10
	li $v0 1
	syscall

# initialize variables to help find max
maxInit:
	# index of array
	li $t0 36
	# running max stored in register $t2
	lw $t2 arr($t0)
	
# loops through the 10 values and sets $t2 to the max
findMax:
	blt $t0 0 printMax #sentinel
	subu $t0 $t0 4
	lw $t1 arr($t0)
	bge $t1 $t2 greater
	j findMax

# if a value is greater than the current max, set $t2 to that value
greater:
	move $t2 $t1
	j findMax
	
printMax:
	li $v0 4 
	la $a0 max
	syscall
	
	li $v0 1
	move $a0 $t2
	syscall

# initialize variables to help find min	
initMin:
	# index of array
	li $t0 36
	# running min stored in register $t2
	li $t2 1000

# same logic as finding the max
findMin:
	blt $t0 0 printMin #sentinel
	lw $t1 arr($t0)
	subu $t0 $t0 4
	ble $t1 $t2 less
	j findMin
	
less:
	move $t2 $t1
	j findMin
	
printMin:
	li $v0 4 
	la $a0 min
	syscall
	
	li $v0 1
	move $a0 $t2
	syscall
	
	li $v0 10 # normal termination
	syscall