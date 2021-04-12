"""
Created by Joseph Edradan
Github: https://github.com/josephedradan

Date created: 4/11/2021

Purpose:
    Automatically runs all the files (text files) in the /programs folder of this project using the already compiled
    Interpreter.class file along with your python interpreter assuming that you have Windows...

Details:

Description:

Notes:

    To execute this script use
        python autorun.py

    Assumes that cwd is where this file is located
    Assumes that you have python >= python 3.6
    Assumes that you have already compiled the java files
    Assumes that you are using Windows
    Assumes that you have no unnecessary prints in your java files (This script compares terminal outputs)

    Recommend that the Java Pseudo Python Interpreter support // for int division so that the Python Interpreter and the
    Java Pseudo Python Interpreter match outputs.

        complex-for-statement.txt
            Python Interpreter
                Uses floats for division which results in
                ['55', '66', '78', '91', '105', '96', '136', '153', '171', '190', '210']

            Java Pseudo Python Interpreter
                Uses ints for division which results in
                ['18', '12', '28', '14', '24', '24', '31', '18', '39', '20', '42']

        complex-function.txt
            Python Interpreter
                Uses floats for division which results in
                ['27.0']

            Java Pseudo Python Interpreter
                Uses ints for division which results in
                ['27']

        complex-function.txt
            Python Interpreter
                Uses floats for division which results in
                ['0.0', '0.0']
                from
                    12 - 12 == 0
                    4 - 4 == 0
                    1 / 0 == ZeroDivisionError: float division by zero

            Java Pseudo Python Interpreter
                Uses ints for division which results in
                ['2', '0', '1']

IMPORTANT NOTES:

Explanation:

Reference:
    subprocess — Subprocess management
        https://docs.python.org/3/library/subprocess.html#subprocess.run
"""

import os
import subprocess
import sys

# Location of programs
FOLDER_PROGRAMS = os.path.join(os.getcwd(), "programs")

# Location to start execution
FOLDER_START = os.path.join(os.getcwd(), "out", "production", "interpreter")

# What file to execute with java
TARGET = ".".join(["edu",
                   "csc413",
                   "interpreter",
                   "Interpreter"])


def autorun():
    """
    Run all files in java pseudo python interpreter and python interpreter.

    Notes:
        subprocess kwargs
            shell: Use shell
            capture_output: Return what the terminal prints out
            text: Return text rather than bytes

    :return: None
    """

    # Get user's python interpreter
    python_version = f"Python {'.'.join([str(sys.version_info[i]) for i in range(3)])}"

    # Loop through current dir, dirs, and current files
    for dir_current, dirs, files in os.walk(FOLDER_PROGRAMS):

        counter = 0

        # Execute each file
        for file in files:
            # Get abs file path of program to execute (the txt file)
            file_abs = os.path.join(dir_current, file)

            # CMD EXECUTION (WINDOWS ONLY)
            command_java_execution = "cd {} && java {} {}".format(FOLDER_START, TARGET, file_abs)
            command_python_execution = "python \"{}\"".format(file_abs)

            print("Running file: {}".format(file))
            # os.system(command_java_execution)

            str_output_python = subprocess.run(command_python_execution,
                                               shell=True,
                                               capture_output=True,
                                               text=True).stdout
            list_output_python = str_output_python.splitlines()

            str_output_java = subprocess.run(command_java_execution,
                                             shell=True,
                                             capture_output=True,
                                             text=True).stdout
            list_output_java = str_output_java.splitlines()

            print("{:<35}{}\n"
                  "{:<35}{}".format("{}: ".format(python_version),
                                    list_output_python,
                                    "Java Pseudo Python Interpreter:",
                                    list_output_java))

            if list_output_python == list_output_java:
                counter += 1
                print("Correct")
            else:
                print("Incorrect")
            print()

        print("{} out of {} Correct relative to {}".format(counter, len(files), python_version))


if __name__ == '__main__':
    autorun()

r"""
Running file: advance-for-statement-iteration-return.txt
Python 3.8.4:                      ['111', '0', '0']
Java Pseudo Python Interpreter:    ['111', '0', '0']
Correct

Running file: arithmetic.txt
Python 3.8.4:                      ['3', '9', '21']
Java Pseudo Python Interpreter:    ['3', '9', '21']
Correct

Running file: assign-print-variables.txt
Python 3.8.4:                      ['1', '2', '3', '4']
Java Pseudo Python Interpreter:    ['1', '2', '3', '4']
Correct

Running file: complex-for-statement.txt
Python 3.8.4:                      ['55', '66', '78', '91', '105', '96', '136', '153', '171', '190', '210']
Java Pseudo Python Interpreter:    ['18', '12', '28', '14', '24', '24', '31', '18', '39', '20', '42']
Incorrect

Running file: complex-function.txt
Python 3.8.4:                      ['27.0']
Java Pseudo Python Interpreter:    ['27']
Incorrect

Running file: complex-while-statement.txt
Python 3.8.4:                      ['1', '2', '3', '4', '6', '8', '9', '12', '16', '18', '24', '36', '48', '72', '144']
Java Pseudo Python Interpreter:    ['1', '2', '3', '4', '6', '8', '9', '12', '16', '18', '24', '36', '48', '72', '144']
Correct

Running file: extra-credit.txt
Python 3.8.4:                      ['10']
Java Pseudo Python Interpreter:    ['10']
Correct

Running file: fibonacci-iterative.txt
Python 3.8.4:                      ['0', '1', '1', '2', '3', '5', '8', '13', '21', '34']
Java Pseudo Python Interpreter:    ['0', '1', '1', '2', '3', '5', '8', '13', '21', '34']
Correct

Running file: fibonacci-recursive.txt
Python 3.8.4:                      ['55']
Java Pseudo Python Interpreter:    ['55']
Correct

Running file: if-statements.txt
Python 3.8.4:                      ['3', '9', '5', '8']
Java Pseudo Python Interpreter:    ['3', '9', '5', '8']
Correct

Running file: scopes.txt
Python 3.8.4:                      ['15', '3']
Java Pseudo Python Interpreter:    ['15', '3']
Correct

Running file: simple-for-statement.txt
Python 3.8.4:                      ['0', '1', '2', '3', '4', '5', '6', '7', '8', '9']
Java Pseudo Python Interpreter:    ['0', '1', '2', '3', '4', '5', '6', '7', '8', '9']
Correct

Running file: simple-function.txt
Python 3.8.4:                      ['0.0', '0.0']
Java Pseudo Python Interpreter:    ['2', '0', '1']
Incorrect

Running file: simple-print.txt
Python 3.8.4:                      ['1', '10', '3']
Java Pseudo Python Interpreter:    ['1', '10', '3']
Correct

Running file: simple-while-statement.txt
Python 3.8.4:                      ['0', '1', '2', '3', '4', '5', '6', '7', '8', '9']
Java Pseudo Python Interpreter:    ['0', '1', '2', '3', '4', '5', '6', '7', '8', '9']
Correct

12 out of 15 Correct relative to Python 3.8.4
"""
