# import all the necessary libraries
import codecs
import io
import os
import re
import numpy as np
import requests
import gensim
import keras
import flask

from flask import Flask

from flask import Flask, request, redirect, jsonify, render_template	
import os
import socket
import json
from gensim.models import Word2Vec
from keras import Input, Model
from keras.activations import softmax
from keras.layers import Embedding, LSTM, Dense
from keras.optimizers import RMSprop
from keras.preprocessing.sequence import pad_sequences
from keras.utils import to_categorical
from keras_preprocessing.text import Tokenizer
import numpy as np
from flask import Flask, request, jsonify, render_template
import pickle
import threading
from tensorflow import keras

# Get the data from file
def get_all_conversations():
    all_conversations = []
    with codecs.open("D:/BE Project/Final App/CDACPsychiatricChatbot/Coding Part (Implementation)/Seq2Seq Transformer/Cornell Movie Dataset/cornell movie-dialogs corpus/movie_lines.txt", "rb", encoding="utf-8", errors="ignore") as f:
    
        # split corpus line line
        lines = f.read().split("\n")
        
        # get each conversation
        for line in lines:
        
            # each line has multiple columns divided by '+++$+++'
            all_conversations.append(line.split(" +++$+++ "))
    
    # return all conversation
    return all_conversations

# Dataset is too big hence taking only first 10000 lines
# create a function to get all sorted conversation
def get_all_sorted_chats(all_conversations):
    all_chats = {}
    for tokens in all_conversations[:2000]:

        # if the line is valid - it contains all the metadata
        if len(tokens) > 4:

            # save the line number and the text itself
            # 4 th is the index where actual dialogue is present    
            all_chats[int(tokens[0][1:])] = tokens[4]

    # then sort the result and return list of tuples
    return sorted(all_chats.items(), key=lambda x: x[0])

# create a function to clean the text
def clean_text(text_to_clean):

    # apply all these conditions to clean the text
    res = text_to_clean.lower()
    res = re.sub(r"i'm", "i am", res)
    res = re.sub(r"he's", "he is", res)
    res = re.sub(r"she's", "she is", res)
    res = re.sub(r"it's", "it is", res)
    res = re.sub(r"that's", "that is", res)
    res = re.sub(r"what's", "what is", res)
    res = re.sub(r"where's", "where is", res)
    res = re.sub(r"how's", "how is", res)
    res = re.sub(r"\'ll", " will", res)
    res = re.sub(r"\'ve", " have", res)
    res = re.sub(r"\'re", " are", res)
    res = re.sub(r"\'d", " would", res)
    res = re.sub(r"\'re", " are", res)
    res = re.sub(r"won't", "will not", res)
    res = re.sub(r"can't", "cannot", res)
    res = re.sub(r"n't", " not", res)
    res = re.sub(r"n'", "ng", res)
    res = re.sub(r"'bout", "about", res)
    res = re.sub(r"'til", "until", res)
    res = re.sub(r"[-()\"#/@;:<>{}`+=~|.!?,]", "", res)
    
    # return the clean text
    return res

# create a function to group the lines into conversations
def get_conversation_dict(sorted_chats):
  
    # create a conversation dictionary to store the index and dialouge
    conversation_dict = {}
    
    # create a temporary counter
    counter = 1

    # store all index to one list
    conversation_ids = []

    # iterate through all sorted conversations
    for i in range(1, len(sorted_chats) + 1):

        # for all conversations index range between 1 to len(sorted_chats)
        if i < len(sorted_chats):

            # if the current line number differs from the previous only by 1
            if (sorted_chats[i][0] - sorted_chats[i - 1][0]) == 1:
              
                # then this line is a part of the current conversation
                # if the previous line was not added before,
                # then we should add it now
                if sorted_chats[i - 1][1] not in conversation_ids:
                    conversation_ids.append(sorted_chats[i - 1][1])
                
                # or just append the current line
                conversation_ids.append(sorted_chats[i][1])
                
            # If the difference is more than 1
            # it means new conversation has started and we should clear conversation_ids
            elif (sorted_chats[i][0] - sorted_chats[i - 1][0]) > 1:
                conversation_dict[counter] = conversation_ids
                conversation_ids = []
                counter += 1
            else:
                continue

    # return conversation dictionary with all conversations   
    return conversation_dict

# create a function to prepare the list of questions and answers
def get_clean_q_and_a(conversations_dictionary):

    # Create an questions and answers list
    questions_and_answer = []
    
    # iterate through each conversation
    for current_conversation in conversations_dictionary.values():
      
        # make sure that each conversation contains an even number of lines
        if len(current_conversation) % 2 != 0:
            current_conversation = current_conversation[:-1]

        # convert questions and answers to the list of tuples
        for i in range(0, len(current_conversation), 2):
            questions_and_answer.append((current_conversation[i], current_conversation[i + 1]))

    # zip with * operator unzips tuples into independent lists
    questions, answers = zip(*questions_and_answer)
    
    # get the list of the questions
    questions_list = list(questions)

    # clear questions from contracted forms, non-letter symbols and convert it to lowercase
    clean_questions = list()
      
    for i in range(len(questions_list)):
        clean_questions.append(clean_text(questions_list[i]))

    # get the list of the answers
    answer_list = list(answers)

    # do the same with the answers, but now we need to add 'start' and 'end' words
    clean_answers = list()
    
    for i in range(len(answer_list)):
        clean_answers.append('<START> ' + clean_text(answer_list[i]) + ' <END>')
    
    # return clean answers and clean questions
    return clean_questions, clean_answers

# run all the function to check for progress

# get all the conversation from dataset
conversations = get_all_conversations()

# get the total conversation length
total = len(conversations)
# print("Total conversations in dataset: {}".format(total))

# get all the sorted conversation
all_sorted_chats = get_all_sorted_chats(conversations)

# get the conversation dictionary
conversation_dictionary = get_conversation_dict(all_sorted_chats)

# get the list of questions and answers
questions, answers = get_clean_q_and_a(conversation_dictionary)

# print total number of questions and answers
# print("Questions in dataset: {}".format(len(questions)))
# print("Answers in dataset: {}".format(len(answers)))

# load the model and keras hidden layes
# load the model
model = keras.models.load_model('D:/BE Project/Final App/CDACPsychiatricChatbot/Coding Part (Implementation)/Seq2Seq Transformer/Flask/Model Data/model_2.h5')
    
# load all the layers
enc_inputs = model.layers[0].output
dec_inputs = model.layers[1].output
dec_embedding = model.layers[3].output
enc_outputs, state_h, state_c = model.layers[4].output
enc_states = [state_h, state_c]
dec_dense = model.get_layer("dense")
dec_lstm = model.get_layer("lstm_1")

 #create an inference model encoder
def make_inference_models():
    
    # two inputs for the state vectors returned by encoder
    dec_state_input_h = Input(shape=(200,),name="input_dec_state_h")
    dec_state_input_c = Input(shape=(200,),name="input_dec_state_c")
    dec_states_inputs = [dec_state_input_h, dec_state_input_c]
      
    # these state vectors are used as an initial state 
    # for LSTM layer in the inference decoder
    # third input is the Embedding layer as explained above   
    dec_outputs, state_h, state_c = dec_lstm(dec_embedding, initial_state=dec_states_inputs)
    dec_states = [state_h, state_c]
      
    # Dense layer is used to return OHE predicted word
    dec_outputs = dec_dense(dec_outputs)
    dec_model = Model(inputs=[dec_inputs] + dec_states_inputs, outputs=[dec_outputs] + dec_states)
    
    # single encoder input is a question, represented as a sequence 
    # of integers padded with zeros
    enc_model = Model(inputs=enc_inputs, outputs=enc_states)
    
    return enc_model, dec_model

# run the above function to get the encoding and decoding sequence
enc_model, dec_model = make_inference_models()

# main regular expression
target_regex = '!"#$%&()*+,-./:;<=>?@[\]^_`{|}~\t\n\'0123456789'

# Tokenizer allows to vectorize our corpus by turning each sentence into a sequence of integers where each integer is an index
# of a token in an internal dictionary
tokenizer = Tokenizer(filters=target_regex)
tokenizer.fit_on_texts(questions + answers)

# get the vocab size
VOCAB_SIZE = len(tokenizer.word_index) + 1
# print('Vocabulary size : {}'.format(VOCAB_SIZE))

# Create a function to convert the string into tokens
def str_to_tokens(sentence: str):
    # convert input string to lowercase, then split it by whitespaces
    words = sentence.lower().split()
      
    # then convert to a sequence of integers padded with zeros
    tokens_list = list()
    for current_word in words:
        result = tokenizer.word_index.get(current_word, '')

        # if list is not empty then append the result into token_list
        if result != '':
            tokens_list.append(result)

    # return One Hot Encodding of input string
    return pad_sequences([tokens_list], maxlen=maxlen_questions, padding='post')

# tokenized and add padding to questions
tokenized_questions = tokenizer.texts_to_sequences(questions)
maxlen_questions = max([len(x) for x in tokenized_questions])

# tokenized and add padding to questions
tokenized_answers = tokenizer.texts_to_sequences(answers)
maxlen_answers = max([len(x) for x in tokenized_answers])

def Predictions(inputText):
    # main chatbot questions and answers
    # encode the input sequence into state vectors
    input_query = inputText
    # input_query = input('\nEnter question : ')

    # to continue the conversation
    states_values = enc_model.predict(str_to_tokens(input_query))

    # start with a target sequence of size 1 - word 'start'   
    empty_target_seq = np.zeros((1, 1))
    empty_target_seq[0, 0] = tokenizer.word_index['start']
    stop_condition = False
    decoded_translation = ''

    # loop until true to apply text generation algorithm
    while not stop_condition:
              
        # feed the state vectors and 1-word target sequence to the decoder to produce predictions for the next word
        dec_outputs, h, c = dec_model.predict([empty_target_seq] + states_values)         
                
        # sample the next word using these predictions
        sampled_word_index = np.argmax(dec_outputs[0, -1, :])
        sampled_word = None
                
        # append the sampled word to the target sequence
        for word, index in tokenizer.word_index.items():
            if sampled_word_index == index:
                if word != 'end':
                    decoded_translation += ' {}'.format(word)
                sampled_word = word
                
        # repeat until we generate the end-of-sequence word 'end' or we hit the length of answer limit
        if sampled_word == 'end' or len(decoded_translation.split()) > maxlen_answers:
            stop_condition = True
                
        # prepare next iteration
        empty_target_seq = np.zeros((1, 1))
        empty_target_seq[0, 0] = sampled_word_index
        states_values = [h, c]
          
    # print("Chatbot        :",decoded_translation)
    return decoded_translation

# testing for pickle data
print(Predictions("who"))

app = Flask(__name__)

# Handle the api and send the prediction to the user
@app.route('/response', methods = ['POST'])
def response():

    # Get the information send by andriod
    req = request.get_json()
    input_query = req['userQuery']
    print(input_query)

    # Predict the output
    output_response = Predictions(input_query)
    
    print(output_response)
    # Store the output in req[userQuery]
    req['userQuery'] = output_response
    
    
    # return the json file with output response
    return req

if __name__ == '__main__':
	  app.run(host="127.0.0.1")


'''

app = Flask(__name__)

@app.route('/hello',methods = ['POST'])
def hello():
    req = request.get_json()
    print(req['userQuery'])
    temp = req['userQuery']
    myString = temp[::-1]
    req['userQuery'] = myString
    return req
    
if __name__ == '__main__':
    app.run(host="127.0.0.1") '''