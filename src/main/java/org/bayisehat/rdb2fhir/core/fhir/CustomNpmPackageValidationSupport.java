package org.bayisehat.rdb2fhir.core.fhir;

import ca.uhn.fhir.context.FhirContext;
import org.hl7.fhir.common.hapi.validation.support.NpmPackageValidationSupport;
import org.hl7.fhir.instance.model.api.IBaseResource;
import org.hl7.fhir.utilities.TextFile;
import org.hl7.fhir.utilities.npm.NpmPackage;
import org.jetbrains.annotations.NotNull;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;

public class CustomNpmPackageValidationSupport extends NpmPackageValidationSupport {
    public CustomNpmPackageValidationSupport(@NotNull FhirContext theFhirContext) {
        super(theFhirContext);
    }

    /**
     * NpmPackageValidationSupport does not provide method to load file from path other than class path,
     * So the class is extended to support that behaviour
     * */
    public void loadPackageFromPath(String path) throws IOException {
        InputStream is = new FileInputStream(path);

        try {
            NpmPackage pkg = NpmPackage.fromPackage(is);
            if (pkg.getFolders().containsKey("package")) {
                this.loadResourcesFromPackage(pkg);
                this.loadBinariesFromPackage(pkg);
            }
        } catch (Throwable var6) {
            if (is != null) {
                try {
                    is.close();
                } catch (Throwable var5) {
                    var6.addSuppressed(var5);
                }
            }

            throw var6;
        }

        if (is != null) {
            is.close();
        }
    }

    private void loadResourcesFromPackage(NpmPackage thePackage) {
        NpmPackage.NpmPackageFolder packageFolder = (NpmPackage.NpmPackageFolder)thePackage.getFolders().get("package");
        Iterator var3 = packageFolder.listFiles().iterator();

        while(var3.hasNext()) {
            String nextFile = (String)var3.next();
            if (nextFile.toLowerCase(Locale.US).endsWith(".json")) {
                String input = new String((byte[])packageFolder.getContent().get(nextFile), StandardCharsets.UTF_8);
                IBaseResource resource = this.getFhirContext().newJsonParser().parseResource(input);
                super.addResource(resource);
            }
        }

    }

    private void loadBinariesFromPackage(NpmPackage thePackage) throws IOException {
        List<String> binaries = thePackage.list("other");
        Iterator var3 = binaries.iterator();

        while(var3.hasNext()) {
            String binaryName = (String)var3.next();
            this.addBinary(TextFile.streamToBytes(thePackage.load("other", binaryName)), binaryName);
        }

    }
}
