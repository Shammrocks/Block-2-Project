NAME = PasswordStrengthChecker

all: compile

compile: $(NAME).class

$(NAME).class: $(NAME).java
	javac $(NAME).java

run: compile
	java $(NAME)

clean: 
	rm -f $(NAME).class

.PHONY: clean
