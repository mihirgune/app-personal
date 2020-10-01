# take input from user
print("-----------------------------------------------------------------------------------------------------------")
print("\n                                           COVID - 19 REMOTE TEST\n")
print("-----------------------------------------------------------------------------------------------------------")
age = int(input("\nQ.1 Enter your age :- "))
sex = input("\nQ.2 Are you Male or Female? \n  [Please write 'male' if you are else write 'female'] :- ")
lossSmellAndTaste = input("\nQ.3 Do you feel smell and taste? [yes/no] :- ")
cough = input("\nQ.4 Do you have severe or significant persistant cough? [yes/no] :- ")
fatigue = input("\nQ.5 Do you feel fatigue? [yes/no] :- ")
skippedMeals = input("\nQ.6 Last question, Have you skipped a meal in last 7 - 8 days? [yes/no] :- ")
print("\n-----------------------------------------------------------------------------------------------------------")

# input data processing
sex = sex.lower()
lossSmellAndTaste = lossSmellAndTaste.lower()
cough = cough.lower()
fatigue = fatigue.lower()
skippedMeals = skippedMeals.lower()

# changing the input to actual values
if sex == "male":
    sexInput = 1
else:
    sexInput = 0

if lossSmellAndTaste == "yes":
    lossSmellAndTasteInput = 0
else:
    lossSmellAndTasteInput = 1

if cough == "yes":
    coughInput = 1
else:
    coughInput = 0

if fatigue == "yes":
    fatigueInput = 1
else:
    fatigueInput = 0

if skippedMeals == "yes":
    skippedMealsInput = 1
else:
    skippedMealsInput = 0

# create array of values
arrSymptoms = []
arrSymptoms.append(lossSmellAndTasteInput)
arrSymptoms.append(coughInput)
arrSymptoms.append(fatigueInput)
arrSymptoms.append(skippedMealsInput)

# calculate the predictions
predictionsModel = - 1.32 - (0.01 * age) + (0.44 * sexInput) + (1.75 * lossSmellAndTasteInput) + (0.31 * coughInput) + (0.49 * fatigueInput) + (0.39 * skippedMealsInput)

# calculate the sigmoid value of prediction
import math

def convertValue(x):
    return (math.exp(x) / (1 + math.exp(x)))

probability = convertValue(predictionsModel)
print("\n-----------------------------------------------------------------------------------------------------------")
print("\nProbability of Contracting COVID - 19 :- ",probability)
print("\n-----------------------------------------------------------------------------------------------------------")

# check for covid - 19
if probability <= 0.5:
    if arrSymptoms.count(1)==0:
        print("\n-----------------------------------------------------------------------------------------------------------")
        print("\nYou have no Covid - 19 symptoms but still you take care of yourself.")
        print("\n-----------------------------------------------------------------------------------------------------------")
    else:
        print("\n-----------------------------------------------------------------------------------------------------------")
        print("\nYou might have starting symptoms. Please contact your family doctor.")
        print("\n-----------------------------------------------------------------------------------------------------------")
else:
    if (probability > 0.62 and probability < 0.69) or (probability > 0.55 and probability < 0.58):
        if arrSymptoms.count(1)==0:
            print("\n-----------------------------------------------------------------------------------------------------------")
            print("\nYou have no Covid - 19 symptoms but still you take care of yourself.")
            print("\n-----------------------------------------------------------------------------------------------------------")
        else:
            print("\n-----------------------------------------------------------------------------------------------------------")
            print("\nYou might have starting symptoms. Please contact your family doctor.")
            print("\n-----------------------------------------------------------------------------------------------------------")
    else:
        print("\n-----------------------------------------------------------------------------------------------------------")
        print("\nYou might have Covid - 19 symptoms and you should immediately go to hospital for further checkups.")
        print("\n-----------------------------------------------------------------------------------------------------------")