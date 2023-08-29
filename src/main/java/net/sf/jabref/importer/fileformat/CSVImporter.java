package net.sf.jabref.importer.fileformat;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

import net.sf.jabref.model.entry.BibEntry;
import net.sf.jabref.model.entry.BibtexEntryTypes;

import net.sf.jabref.importer.ImportFormatReader;
import net.sf.jabref.importer.OutputPrinter;

public class CSVImporter extends ImportFormat {

    @Override
    public String getFormatName() {
        return "CSV";
    }

    @Override
    public String getExtensions() {
        return "csv";
    }

    @Override
    public String getDescription() {
        return "Importa arquivos CSV (apenas Book)";
    }

    @Override
    public boolean isRecognizedFormat(InputStream in) throws IOException {
        return true;
    }

    @Override
    public List<BibEntry> importEntries(InputStream stream, OutputPrinter printer) throws IOException {
        List<BibEntry> bibitems = new ArrayList<>();
        BufferedReader in = new BufferedReader(ImportFormatReader.getReaderDefaultEncoding(stream));

        String line = in.readLine();
        while (line != null) {
            if (!line.trim().isEmpty()) {
                String[] fields = line.split(",");
                BibEntry be = new BibEntry();
                be.setType(BibtexEntryTypes.BOOK);
                be.setField("title", fields[0].trim());
                be.setField("publisher", fields[1].trim());
                be.setField("year", fields[2].trim());
                be.setField("author", fields[3].trim());
                be.setField("editor", fields[4].trim());
                be.setField("bibtexkey", fields[5].trim());
                bibitems.add(be);
                line = in.readLine();
            }
        }
        return bibitems;
    }

}