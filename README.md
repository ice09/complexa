# Complexa

Complexa is a complex chat bot built on OpenAI API.

<img src="docs/complexa_logo.png" width="300"/>

## Architecture Overview

![complexa_overview.png](docs/complexa_overview.png)

This is mainly a wrapper around these great projects:

* [Simple-OpenAI](https://github.com/sashirestela/simple-openai): A Java library to use the OpenAI Api in the simplest possible way.
* [LangChain4j](https://docs.langchain4j.dev/): Supercharge your Java application with the power of LLMs.

## Todos

- [x] Speech recognition: Detect empty WAVs
- [x] Upload non-empty WAVs to Whisper
- [x] Scan transcription for activation words
- [x] On activation: Play "go on" ("ich höre")
- [x] Record up to 5 secs until 1 sec empty
- [x] Upload to speech recognition
- [x] Upload transcription to gpt4o-mini
- [x] Upload answer to tts
- [x] Play WAV to user

