# TablatureConverter 🎶

---

### Table of Contents

- [Description](#description)
- [Getting Started](#getting-started)
- [Troubleshooting] (#troubleshooting)
- [Usage Scenarios](#usage-scenarios)
- [Usage Instructions](#usage_instructions)
- [Contributors](#contributors)
- [License](#license)

---

## Description
Tablature notation is a way of writing down an instrument’s parts in an accessible manner. The tablature in the context of this project will refer to guitar tablature. Although tablature is widely used for its ease of use, most tabs exist in a text format, and there does not exist an easy way to turn guitar tab text into a more featured digital file format.

The software product will consist of a desktop application. Upon the upload of a tablature in *.txt form, the system will convert the tab to a MusicXML file format.

### Sample Guitar Tablature

```
e|------------------------------------------------------------|
B|------------------------------------------------------------|
G|------------------------------------------------------------|
D|------------------------------------------------------------|
A|--2-0-----2-0-----------0-0-0-0-2-0-------------------------|
E|------3-------3--3-3-3-3------------3-----------------------|
```


### Technologies

- Java - Programming Language
- JavaFx - Client Application Platform for Desktop
- JAXB - XML Binding

---

## Getting Started

TablatureConverter requires the the following to run:
- Download [IntelliJ](https://www.jetbrains.com/idea/download/#section=windows) or [Eclipse](https://www.eclipse.org/downloads/)
- Download [JavaFX SDK](https://gluonhq.com/products/javafx/)


### How to Access for Eclipse

1. Download ZIP in repository and unzip the file or click on the clipboard icon in order to clone URI on eclipse
 <img width="943" alt="cloneURI" src="https://user-images.githubusercontent.com/68396714/109436020-70b33400-79eb-11eb-9d44-2e01368e05ed.PNG">


![Code](https://res.cloudinary.com/practicaldev/image/fetch/s--L5zkGG8u--/c_limit%2Cf_auto%2Cfl_progressive%2Cq_auto%2Cw_880/https://dev-to-uploads.s3.amazonaws.com/i/95r8bqqsnt0losag50b1.png)

2. i) Open Eclipse -> Import -> Exisiting project into workspace -> Select the unzipped file "TablatureConverter"
                                                              OR
   ii.) Open Eclipse -> Import -> Git -> Project from git -> Clone URI -> Paste the URI -> Finish
   
3. Add text below to VM arguments in run configurations
```
--module-path "path_here" --add-modules javafx.controls,javafx.fxml
```
4. Go to src/GUI, open application package, right click Main.java -> Run As -> Java Application
5. Program is now running! The outputted MusicXML file is in the src/sample package.


## Usage-Scenario

Scenario: A successful musicXML conversion attempt using the Tablature Converter program.
John Smith imports the project into his Eclipse workspace

John runs the project using build.gradle 

The Program runs

John chooses the option to upload File

The .txt file chosen by John shows on the textview

John decides he wants to edit the text file using the textview

John clicks on the convert button after he was satisfied with his edits

The program takes him to another page which shows "Conversion Complete"

John realized that he chose the wrong text file

John clicks on the "back" button

The program takes him back to the home page

John chooses the right file and clicks on convert

The program takes him back to the Conversion Complete page

John clicks on "Open MusicXML"

The program opens the converted MusicXML in the browser

John downloads the MusicXML file


## Contributors

- https://github.com/jamesmqle
- https://github.com/tuansydau
- https://github.com/tizreal
- https://github.com/amin45mh

---

## Contact Us

Tuan Dau - tdau@my.yorku.ca

James Le - jamesmql@my.yorku.ca

Temi Odunfa- odunifa.temi@gmail.com

---

## License

MIT License

Copyright (c) 2018 Othneil Drew

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.

