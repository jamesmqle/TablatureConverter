# TablatureConverter 🎶

---

### Table of Contents

- [Description](#description)
- [Getting Started](#getting-started)
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

1. Download ZIP in repository and unzip the file

![Code](https://res.cloudinary.com/practicaldev/image/fetch/s--L5zkGG8u--/c_limit%2Cf_auto%2Cfl_progressive%2Cq_auto%2Cw_880/https://dev-to-uploads.s3.amazonaws.com/i/95r8bqqsnt0losag50b1.png)

2. Open Eclipse -> Import -> Exisiting project into workspace -> Select the unzipped file "TablatureConverter"
3. Add text below to VM arguments in run configurations
```
--module-path "path_here" --add-modules javafx.controls,javafx.fxml
```
4. Go to src/GUI, open application package, right click Main.java -> Run As -> Java Application
5. Program is now running! The outputted MusicXML file is in the src/sample package.

        
## Contributors

- https://github.com/jamesmqle
- https://github.com/tuansydau
- https://github.com/tizreal
- https://github.com/amin45mh

---

## Contact Us

Tuan Dau - tdau@my.yorku.ca

James Le - 

Temi - odunifa.temi@gmail.com

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

