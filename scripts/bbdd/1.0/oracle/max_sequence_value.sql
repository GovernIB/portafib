select max(mx)
from
(
select max(AlgorismeDeFirmaID) as mx from PFI_AlgorismeDeFirma
union
select max(AnnexFirmatID) as mx from PFI_AnnexFirmat
union
select max(AnnexID) as mx from PFI_Annex
union
select max(BitacolaID) as mx from PFI_Bitacola
union
select max(BlocDeFirmesID) as mx from PFI_BlocDeFirmes
union
select max(ColaboracioDelegacioID) as mx from PFI_ColaboracioDelegacio
union
select max(CustodiaInfoID) as mx from PFI_CustodiaInfo
union
select max(EstatDeFirmaID) as mx from PFI_EstatDeFirma
union
select max(FirmaID) as mx from PFI_Firma
union
select max(FitxerID) as mx from PFI_Fitxer
union
select max(FluxDeFirmesID) as mx from PFI_FluxDeFirmes
union
select max(GrupEntitatID) as mx from PFI_GrupEntitat
union
select max(GrupEntitatUsuariEntitatID) as mx from PFI_GrupEntitatUsuariEntitat
union
select max(MetadadaID) as mx from PFI_Metadada
union
select max(PermisGrupPlantillaID) as mx from PFI_PermisGrupPlantilla
union
select max(PermisUsuariPlantillaID) as mx from PFI_PermisUsuariPlantilla
union
select max(PeticioDeFirmaID) as mx from PFI_PeticioDeFirma
union
select max(PosicioPaginaID) as mx from PFI_PosicioPagina
union
select max(PosicioTaulaFirmesID) as mx from PFI_PosicioTaulaFirmes
union
select max(PrioritatID) as mx from PFI_Prioritat
union
select max(TipusDocumentID) as mx from PFI_TipusDocument
union
select max(TipusEstatDeFirmaFinalID) as mx from PFI_TipusEstatDeFirmaFinal
union
select max(TipusEstatDeFirmaInicialID) as mx from PFI_TipusEstatDeFirmaInicial
union
select max(TipusEstatPeticioDeFirmaID) as mx from PFI_TipusEstatPeticioDeFirma
union
select max(TipusFirmaID) as mx from PFI_TipusFirma
union
select max(TipusMetadadaID) as mx from PFI_TipusMetadada
union
select max(TipusNotificacioID) as mx from PFI_TipusNotificacio
union
select max(TraduccioID) as mx from PFI_Traduccio
);