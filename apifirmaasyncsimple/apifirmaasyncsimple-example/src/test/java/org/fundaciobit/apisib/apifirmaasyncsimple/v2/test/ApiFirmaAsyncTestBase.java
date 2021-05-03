package org.fundaciobit.apisib.apifirmaasyncsimple.v2.test;

import org.fundaciobit.apisib.apifirmaasyncsimple.v2.ApiFirmaAsyncSimple;
import org.fundaciobit.apisib.apifirmaasyncsimple.v2.beans.FirmaAsyncSimpleFile;
import org.fundaciobit.apisib.apifirmaasyncsimple.v2.beans.FirmaAsyncSimpleReviser;
import org.fundaciobit.apisib.apifirmaasyncsimple.v2.beans.FirmaAsyncSimpleSignature;
import org.fundaciobit.apisib.apifirmaasyncsimple.v2.beans.FirmaAsyncSimpleSignatureBlock;
import org.fundaciobit.apisib.apifirmaasyncsimple.v2.beans.FirmaAsyncSimpleSignatureRequestBase;
import org.fundaciobit.apisib.apifirmaasyncsimple.v2.beans.FirmaAsyncSimpleSignatureRequestInfo;
import org.fundaciobit.apisib.apifirmaasyncsimple.v2.beans.FirmaAsyncSimpleSignatureRequestState;
import org.fundaciobit.apisib.apifirmaasyncsimple.v2.beans.FirmaAsyncSimpleSignatureRequestWithFlowTemplateCode;
import org.fundaciobit.apisib.apifirmaasyncsimple.v2.beans.FirmaAsyncSimpleSignatureRequestWithSignBlockList;
import org.fundaciobit.apisib.apifirmaasyncsimple.v2.beans.FirmaAsyncSimpleSigner;
import org.fundaciobit.apisib.apifirmaasyncsimple.v2.jersey.ApiFirmaAsyncSimpleJersey;
import org.fundaciobit.apisib.apifirmaasyncsimple.v2.test.actors.Destinatari;
import org.fundaciobit.apisib.apifirmaasyncsimple.v2.test.actors.Revisor;
import org.fundaciobit.apisib.core.exceptions.AbstractApisIBException;
import org.fundaciobit.pluginsib.core.utils.FileUtils;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Properties;

public class ApiFirmaAsyncTestBase {

    protected static ApiFirmaAsyncSimple api;
    private static String apiProfile;

    protected static void initApi(Properties properties) {
        api = new ApiFirmaAsyncSimpleJersey(
                properties.getProperty("api.endpoint"),
                properties.getProperty("api.username"),
                properties.getProperty("api.password"));
        apiProfile = properties.getProperty("api.profile");
    }

    protected long crearPeticioWithFlow(String flow) {
        try {
            return api.createAndStartSignatureRequestWithFlowTemplateCode(
                    getRequestWithFlowTemplateCode("hola.pdf", "application/pdf", flow));
        } catch (AbstractApisIBException e) {
            throw new RuntimeException("Error creant petició: " + e.getMessage(), e);
        }
    }

    protected long crearPeticioDestinataris(Destinatari... destinataris) {
        try {
            return api.createAndStartSignatureRequestWithSignBlockList(
                    getRequestWithSequentialBlockList("hola.pdf", "application/pdf", destinataris));
        } catch (AbstractApisIBException e) {
            throw new RuntimeException("Error creant petició: " + e.getMessage(), e);
        }
    }

    protected long crearPeticioDestinatarisParallel(int minSigners, int obligatoris, Destinatari... destinataris) {
        try {
            return api.createAndStartSignatureRequestWithSignBlockList(
                    getRequestWithParallelBlockList("hola.pdf", "application/pdf",
                            minSigners, obligatoris, destinataris));
        } catch (AbstractApisIBException e) {
            throw new RuntimeException("Error creant petició: " + e.getMessage(), e);
        }
    }

    protected long crearPeticioDestinariRevisor(Destinatari destinatari, Revisor revisor) {
        try {
            return api.createAndStartSignatureRequestWithSignBlockList(
                    getRequestWithSequentialBlockList("hola.pdf", "application/pdf", destinatari, revisor));
        } catch (AbstractApisIBException e) {
            throw new RuntimeException("Error creant petició", e);
        }
    }

    protected long crearPeticioDestinariRevisors(Destinatari destinatari, int minRevisers, int obligatoris, Revisor... revisors) {
        try {
            return api.createAndStartSignatureRequestWithSignBlockList(
                    getRequestWithSequentialBlockList("hola.pdf", "application/pdf",
                            destinatari, minRevisers, obligatoris, revisors));
        } catch (AbstractApisIBException e) {
            throw new RuntimeException("Error creant petició", e);
        }
    }

    protected long crearPeticioDestinariRevisorDestintariRevisor(Destinatari dest1, Revisor revi1,
                                                                 Destinatari dest2, Revisor revi2) {
        try {
            return api.createAndStartSignatureRequestWithSignBlockList(
                    getRequestWithSequentialBlockList("hola.pdf", "application/pdf", dest1, revi1, dest2, revi2));
        } catch (AbstractApisIBException e) {
            throw new RuntimeException("Error creant petició", e);
        }
    }

    protected long crearPeticioDestinariRevisorDestintariRevisorParallel(Destinatari dest1, Revisor revi1,
                                                                         Destinatari dest2, Revisor revi2) {
        try {
            return api.createAndStartSignatureRequestWithSignBlockList(
                    getRequestWithParallelBlockList("hola.pdf", "application/pdf", dest1, revi1, dest2, revi2));
        } catch (AbstractApisIBException e) {
            throw new RuntimeException("Error creant petició", e);
        }
    }

    protected long statusPeticio(long peticio) {
        try {
            FirmaAsyncSimpleSignatureRequestState state =
                    api.getSignatureRequestState(new FirmaAsyncSimpleSignatureRequestInfo(peticio, "ca"));
            return state.getState();
        } catch (AbstractApisIBException e) {
            throw new RuntimeException("Error comprovant estat de petició", e);
        }
    }

    protected void deletePeticio(long peticio) {
        try {
            FirmaAsyncSimpleSignatureRequestInfo info = new FirmaAsyncSimpleSignatureRequestInfo(peticio, "ca");
            api.deleteSignatureRequest(info);
        } catch (AbstractApisIBException e) {
            throw new RuntimeException("Error esborrant petició", e);
        }
    }

    private FirmaAsyncSimpleSignatureRequestWithFlowTemplateCode getRequestWithFlowTemplateCode(
            String fileName, String mime, String flowTemplate) {
        return new FirmaAsyncSimpleSignatureRequestWithFlowTemplateCode(getRequestBase(fileName, mime), flowTemplate);
    }

    private FirmaAsyncSimpleSignatureRequestWithSignBlockList getRequestWithSequentialBlockList(
            String fileName, String mime, Destinatari... destinataris) {
        return new FirmaAsyncSimpleSignatureRequestWithSignBlockList(
                getRequestBase(fileName, mime), getSequentialBlocks(destinataris));
    }

    private FirmaAsyncSimpleSignatureRequestWithSignBlockList getRequestWithSequentialBlockList(
            String fileName, String mime, Destinatari destinatari, Revisor revisor) {
        return new FirmaAsyncSimpleSignatureRequestWithSignBlockList(
                getRequestBase(fileName, mime), getSequentialBlocks(destinatari, revisor));
    }

    private FirmaAsyncSimpleSignatureRequestWithSignBlockList getRequestWithSequentialBlockList(
            String fileName, String mime, Destinatari destinatari, int minRevisers, int obligatoris, Revisor... revisors) {
        return new FirmaAsyncSimpleSignatureRequestWithSignBlockList(
                getRequestBase(fileName, mime), getSequentialBlocks(destinatari, minRevisers, obligatoris, revisors));
    }

    private FirmaAsyncSimpleSignatureRequestWithSignBlockList getRequestWithSequentialBlockList(
            String fileName, String mime, Destinatari dest1, Revisor revi1, Destinatari dest2, Revisor revi2) {
        return new FirmaAsyncSimpleSignatureRequestWithSignBlockList(
                getRequestBase(fileName, mime), getSequentialBlocks(dest1, revi1, dest2, revi2));
    }

    private FirmaAsyncSimpleSignatureRequestWithSignBlockList getRequestWithParallelBlockList(
            String fileName, String mime, int minSigners, int obligatoris, Destinatari... destinataris) {
        return new FirmaAsyncSimpleSignatureRequestWithSignBlockList(
                getRequestBase(fileName, mime), getParallelBlocks(minSigners, obligatoris, destinataris));
    }

    private FirmaAsyncSimpleSignatureRequestWithSignBlockList getRequestWithParallelBlockList(
            String fileName, String mime, Destinatari dest1, Revisor revi1, Destinatari dest2, Revisor revi2) {
        return new FirmaAsyncSimpleSignatureRequestWithSignBlockList(
                getRequestBase(fileName, mime), getParallelBlocks(dest1, revi1, dest2, revi2));
    }

    private FirmaAsyncSimpleSignatureRequestBase getRequestBase(String fileName, String mime) {
        return new FirmaAsyncSimpleSignatureRequestBase(apiProfile,
                "Petició test selenium " + System.currentTimeMillis(),
                "Descripció test selenium", "Això és el reason", getSimpleFile(fileName, mime),
                null, 8L, "Publicació", "ca", "ca",
                FirmaAsyncSimpleSignatureRequestWithSignBlockList.PRIORITY_NORMAL_NORMAL, "sender name",
                "sender description", null, null, null, null,
                null, "Additional information", null);
    }

    private FirmaAsyncSimpleFile getSimpleFile(String fileName, String mime) {
        try {
            byte[] bytes = FileUtils.readFromFile(new File("./testfiles/" + fileName));
            return new FirmaAsyncSimpleFile(fileName, mime, bytes);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private FirmaAsyncSimpleSignatureBlock[] getSequentialBlocks(Destinatari... destinataris) {
        FirmaAsyncSimpleSignatureBlock[] blocks = new FirmaAsyncSimpleSignatureBlock[destinataris.length];

        for (int i = 0; i < destinataris.length; i++) {
            blocks[i] = new FirmaAsyncSimpleSignatureBlock(1,
                    Collections.singletonList(getSimpleSignature(destinataris[i])));
        }

        return blocks;
    }

    private FirmaAsyncSimpleSignatureBlock[] getSequentialBlocks(Destinatari destinatari, Revisor revisor) {
        FirmaAsyncSimpleSignatureBlock[] blocks = new FirmaAsyncSimpleSignatureBlock[1];

        blocks[0] = new FirmaAsyncSimpleSignatureBlock(1,
                Collections.singletonList(getSimpleSignature(destinatari, revisor)));
        return blocks;
    }

    private FirmaAsyncSimpleSignatureBlock[] getSequentialBlocks(Destinatari destinatari,
                                                                 int minRevisers, int obligatoris, Revisor... revisors) {
        FirmaAsyncSimpleSignatureBlock[] blocks = new FirmaAsyncSimpleSignatureBlock[1];

        blocks[0] = new FirmaAsyncSimpleSignatureBlock(1,
                Collections.singletonList(getSimpleSignature(destinatari, minRevisers, obligatoris, revisors)));
        return blocks;
    }

    private FirmaAsyncSimpleSignatureBlock[] getSequentialBlocks(Destinatari dest1, Revisor revi1,
                                                                 Destinatari dest2, Revisor revi2) {

        FirmaAsyncSimpleSignatureBlock[] blocks = new FirmaAsyncSimpleSignatureBlock[2];
        blocks[0] = new FirmaAsyncSimpleSignatureBlock(1,
                Collections.singletonList(getSimpleSignature(dest1, revi1)));
        blocks[1] = new FirmaAsyncSimpleSignatureBlock(1,
                Collections.singletonList(getSimpleSignature(dest2, revi2)));
        return blocks;
    }

    private FirmaAsyncSimpleSignatureBlock[] getParallelBlocks(int minSigners, int obligatoris,
                                                               Destinatari... destinataris) {

        FirmaAsyncSimpleSignatureBlock[] blocks = new FirmaAsyncSimpleSignatureBlock[1];
        List<FirmaAsyncSimpleSignature> signatureList = new ArrayList<FirmaAsyncSimpleSignature>(destinataris.length);
        for (int i = 0; i < destinataris.length; i++) {
            signatureList.add(getSimpleSignature(destinataris[i], (i < obligatoris)));
        }
        blocks[0] = new FirmaAsyncSimpleSignatureBlock(minSigners, signatureList);
        return blocks;
    }

    private FirmaAsyncSimpleSignatureBlock[] getParallelBlocks(Destinatari dest1, Revisor revi1,
                                                               Destinatari dest2, Revisor revi2) {

        FirmaAsyncSimpleSignatureBlock[] blocks = new FirmaAsyncSimpleSignatureBlock[1];
        List<FirmaAsyncSimpleSignature> signatureList = new ArrayList<FirmaAsyncSimpleSignature>(2);
        signatureList.add(getSimpleSignature(dest1, revi1));
        signatureList.add(getSimpleSignature(dest2, revi2));

        blocks[0] = new FirmaAsyncSimpleSignatureBlock(2,
                signatureList);
        return blocks;
    }

    private FirmaAsyncSimpleSignature getSimpleSignature(Destinatari destinatari) {
        return getSimpleSignature(destinatari, true);
    }

    private FirmaAsyncSimpleSignature getSimpleSignature(Destinatari destinatari, boolean required) {
        return new FirmaAsyncSimpleSignature(
                getSimpleSigner(destinatari), required, null, 0, null);
    }

    private FirmaAsyncSimpleSignature getSimpleSignature(Destinatari destinatari, Revisor reviser) {
        FirmaAsyncSimpleSignature signature = getSimpleSignature(destinatari);
        if (reviser != null) {
            signature.setMinimumNumberOfRevisers(1);
            signature.setRevisers(Collections.singletonList(getSimpleReviser(reviser)));
        }
        return signature;
    }

    private FirmaAsyncSimpleSignature getSimpleSignature(Destinatari destinatari, int minRevisers, int obligatoris,
                                                         Revisor... revisers) {
        FirmaAsyncSimpleSignature signature = getSimpleSignature(destinatari);
        if (revisers.length > 0) {
            signature.setMinimumNumberOfRevisers(minRevisers);
            List<FirmaAsyncSimpleReviser> reviserList = new ArrayList<FirmaAsyncSimpleReviser>(revisers.length);
            for (int i = 0; i < revisers.length; i++) {
                reviserList.add(getSimpleReviser(revisers[i], (i < obligatoris)));
            }
            signature.setRevisers(reviserList);
        }
        return signature;
    }

    private FirmaAsyncSimpleSigner getSimpleSigner(Destinatari destinatari) {
        FirmaAsyncSimpleSigner signer = new FirmaAsyncSimpleSigner();
        if (destinatari.getAdministrationId() != null) {
            signer.setAdministrationID(destinatari.getAdministrationId());
        } else {
            signer.setUsername(destinatari.getUsername());
        }
        return signer;
    }

    private FirmaAsyncSimpleReviser getSimpleReviser(Revisor revisor) {
        return getSimpleReviser(revisor, true);
    }

    private FirmaAsyncSimpleReviser getSimpleReviser(Revisor revisor, boolean required) {
        FirmaAsyncSimpleReviser reviser = new FirmaAsyncSimpleReviser();
        reviser.setAdministrationID(revisor.getAdministrationId());
        reviser.setRequired(required);
        return reviser;
    }
}
