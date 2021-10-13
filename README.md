# Projekt RentACar
Author: Bozidar Spasenovic

### Beschreibung
Die Idee des RentACar Projektes ist ein RestService der ein Autoverleih mit den einzelnen Daten simuliert.
Es gibt eine Vermietung, einen Kunden und mehrere Fahrzeuge die zum vermieten bereit sind.


# Class-Diagram
![cld](asciidocs/images/cld.png)

# Use Case-Diagram
![ucd](asciidocs/images/ucd.png)


## GH-pages commands for windows

1. ```.\part1.sh```

2. ``docker run --rm -v ${PWD}/docs:/documents asciidoctor/docker-asciidoctor /bin/bash -c "asciidoctor -r asciidoctor-diagram -a icons=font -a experimental=true -a source-highlighter=rouge -a rouge-theme=github -a rouge-linenums-mode=inline -a docinfo=shared -a imagesdir=images -a toc=left -a toclevels=2 -a sectanchors=true -a sectnums=true -a favicon=themes/favicon.png -a sourcedir=src/main/java -b html5 '*.adoc' && rm -rf ./.asciidoctor && echo Done"``

3. ```.\part2.sh```

4. ``.\finisher.sh``
