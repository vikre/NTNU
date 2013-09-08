build:
	pdflatex --output-directory out/ Assignment1.tex
	bibtex out/Assignment1.aux
	pdflatex --output-directory out/ Assignment1.tex
	pdflatex --output-directory out/ Assignment1.tex
out:
	@mkdir -p out

clean:
	@rm -fR out

open: build
	@open out/Assignment1.pdf