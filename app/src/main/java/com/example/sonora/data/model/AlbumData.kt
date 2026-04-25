package com.example.sonora.data.model

import com.example.sonora.R


data class PlaylistTrack(
    val title: String,
    val durationSeconds: Int,
    val releaseDate: String,
    val audioResId: Int
)


data class AlbumInfo(
    val id: String,
    val title: String,
    val artist: String,
    val coverRes: Int,
    val tracks: List<PlaylistTrack>
)

object AlbumRepository {
    val albums = listOf(



        AlbumInfo("gal_tropical", "Gal Tropical", "Gal Costa", R.drawable.img_album_galtropical, listOf(
            PlaylistTrack("Balancê", 195, "Jul 1979", R.raw.balance_audio),
            PlaylistTrack("Meu Nome é Gal", 208, "Jul 1979",R.raw.meunome_audio),
            PlaylistTrack("Força Estranha", 220, "Jul 1979",R.raw.forca_audio),
            PlaylistTrack("Índia", 255, "Jul 1979", R.raw.india_audio),
            PlaylistTrack("Estrada do sol", 190, "Jul 1979", R.raw.estrada_audio),
            PlaylistTrack("Samba Rasgado", 182, "Jul 1979", R.raw.samba_audio),
            PlaylistTrack("A preta do acarajé", 215, "Jul 1979", R.raw.apreta_audio),
            PlaylistTrack("Dez Anos", 200, "Jul 1979", R.raw.dezanos_audio),
            PlaylistTrack("Noites Cariocas", 210, "Jul 1979", R.raw.noites_audio),
            PlaylistTrack("Olha", 210, "Jul 1979", R.raw.olha_audio),
            PlaylistTrack("O Bater do Tambor", 210, "Jul 1979", R.raw.obater_audio),
            PlaylistTrack("Juventude Transviada", 210, "Jul 1979", R.raw.juventude_audio)
        )),

        AlbumInfo("los_hermanos", "Los Hermanos", "Los Hermanos", R.drawable.img_album_loshermanos, listOf(
            PlaylistTrack("Tenha Dó", 205, "Aug 1999",  R.raw.tenhado_audio),
            PlaylistTrack("Quem Sabe", 188, "Aug 1999", R.raw.quemsabe_audio),
            PlaylistTrack("Barbara", 195, "Aug 1999", R.raw.barbara_audio),
            PlaylistTrack("Primavera", 240, "Aug 1999", R.raw.primavera_audio),
            PlaylistTrack("Anna Júlia", 212, "Aug 1999", R.raw.anna_audio),
            PlaylistTrack("Onze Dias", 180, "Aug 1999", R.raw.onze_audio),
            PlaylistTrack("Pierrot", 245, "Aug 1999", R.raw.pierot_audio),
            PlaylistTrack("Azedume", 175, "Aug 1999", R.raw.azedume_audio),
            PlaylistTrack("Descoberta", 190, "Aug 1999",R.raw.descoberta_audio),
            PlaylistTrack("Vai Embora", 200, "Aug 1999", R.raw.vaiembora_audio),
            PlaylistTrack("Sem Ter Você", 225, "Aug 1999", R.raw.semter_audio),
            PlaylistTrack("Aline", 198, "Aug 1999", R.raw.aline_audio),
            PlaylistTrack("Outro Alguem", 205, "Aug 1999", R.raw.outro_audio),
            PlaylistTrack("Lagrimas Sofridas", 205, "Aug 1999", R.raw.lagrimas_audio)
        )),

        AlbumInfo("1975", "Being Funny in a Foreign Language", "The 1975", R.drawable.img_album_1975, listOf(
            PlaylistTrack("The 1975", 250, "Oct 2022", R.raw.the1975_audio),
            PlaylistTrack("Happiness", 303, "Oct 2022", R.raw.happiness_audio),
            PlaylistTrack("Looking for Somebody (To Love)", 168, "Oct 2022", R.raw.lookingfor_audio),
            PlaylistTrack("Part of the Band", 260, "Oct 2022",R.raw.partof_audio),
            PlaylistTrack("Oh Caroline", 212, "Oct 2022",R.raw.caroline_audio),
            PlaylistTrack("I’m in Love with You", 262, "Oct 2022",R.raw.iminlove_audio),
            PlaylistTrack("All I Need to Hear", 210, "Oct 2022", R.raw.allineed_audio),
            PlaylistTrack("Wintering", 165, "Oct 2022",R.raw.wintering_audio),
            PlaylistTrack("Human Too", 220, "Oct 2022", R.raw.humantoo_audio),
            PlaylistTrack("About You", 326, "Oct 2022", R.raw.aboutyou_audio),
            PlaylistTrack("When We Are Together", 195, "Oct 2022",R.raw.whenweare_audio)
        )),

        AlbumInfo("midnight_sun", "Midnight Sun", "Zara Larsson", R.drawable.img_album_zara, listOf(
            PlaylistTrack("Midnight Sun", 195, "Jun 2025",R.raw.midnightsun_audio),
            PlaylistTrack("Blue Moon", 210, "Jun 2025",R.raw.moon_audio),
            PlaylistTrack("Pretty Ugly", 188, "Jun 2025",R.raw.pretty_audio),
            PlaylistTrack("Girl's Girl", 205, "Jun 2025",R.raw.girls_audio),
            PlaylistTrack("Crush", 198, "Jun 2025",R.raw.crush_audio),
            PlaylistTrack("Eurosummer", 240, "Jun 2025", R.raw.eurosummer_audio),
            PlaylistTrack("Hot & Sexy", 215, "Jun 2025",R.raw.hotandsexy_audio)
        )),

        AlbumInfo("sade", "Love Deluxe", "Sade", R.drawable.img_album_sade, listOf(
            PlaylistTrack("No Ordinary Love", 432, "Oct 1992",R.raw.nordinary_audio),
            PlaylistTrack("Feel No Pain", 308, "Oct 1992",R.raw.feelno_audio),
            PlaylistTrack("I Couldn't Love You More", 229, "Oct 1992",R.raw.icouldnt_audio),
            PlaylistTrack("Like a Tattoo", 217, "Oct 1992",R.raw.likeatatto_audio),
            PlaylistTrack("Kiss of Life", 350, "Oct 1992",R.raw.kissoflife_audio),
            PlaylistTrack("Cherish the Day", 332, "Oct 1992",R.raw.cherish_audio),
            PlaylistTrack("Pearls", 274, "Oct 1992",R.raw.pearls_audio),
            PlaylistTrack("Bullet Proof Soul", 326, "Oct 1992",R.raw.bullet_audio),
            PlaylistTrack("Mermaid", 263, "Oct 1992",R.raw.mermaid_audio)
        )),

        AlbumInfo("2pac", "Loyal to the Game", "2Pac", R.drawable.img_album_2pac, listOf(
            PlaylistTrack("Soldier Like Me", 230, "Dec 2004",R.raw.soldier_audio),
            PlaylistTrack("The Uppercut", 215, "Dec 2004",R.raw.uppercut_audio),
            PlaylistTrack("Out on Bail", 234, "Dec 2004",R.raw.outonbail_audio),
            PlaylistTrack("Ghetto Gospel", 238, "Dec 2004",R.raw.ghetto_audio),
            PlaylistTrack("Black Cotton", 303, "Dec 2004",R.raw.blackcotton_audio),
            PlaylistTrack("Loyal to the Game", 203, "Dec 2004",R.raw.loyalto_audio),
            PlaylistTrack("Thugs Get Lonely Too", 288, "Dec 2004",R.raw.thugsget_audio),
            PlaylistTrack("N.I.G.G.A.", 182, "Dec 2004",R.raw.nigga_audio),
            PlaylistTrack("Who Do You Love?", 208, "Dec 2004",R.raw.whodoyou_audio),
            PlaylistTrack("Crooked Nigga Too", 175, "Dec 2004",R.raw.crooked_audio),
            PlaylistTrack("Don't You Trust Me?", 295, "Dec 2004",R.raw.dontyou_audio),
            PlaylistTrack("Hennessy", 207, "Dec 2004",R.raw.dontyou_audio),
            PlaylistTrack("Thug 4 Life", 183, "Dec 2004",R.raw.thugfor_audio),
            PlaylistTrack("Po Nigga Blues", 218, "Dec 2004",R.raw.po_audio),
            PlaylistTrack("Hennessey", 210, "Jul 1979", R.raw.hennesseyre_audio)

        )),


        AlbumInfo("bjork_debut", "Debut", "Björk", R.drawable.img_album_bjork, listOf(
            PlaylistTrack("Human Behaviour", 252, "Jul 1993",R.raw.human_audio),
            PlaylistTrack("Crying", 289, "Jul 1993",R.raw.crying_audio),
            PlaylistTrack("Venus as a Boy", 281, "Jul 1993",R.raw.venus_audio),
            PlaylistTrack("There's More to Life Than This", 201, "Jul 1993",R.raw.theresmore_audio),
            PlaylistTrack("Like Someone in Love", 273, "Jul 1993",R.raw.likesomeone_audio),
            PlaylistTrack("Big Time Sensuality", 236, "Jul 1993",R.raw.bigtime_audio),
            PlaylistTrack("One Day", 324, "Jul 1993",R.raw.oneday_audio),
            PlaylistTrack("Aeroplane", 234, "Jul 1993",R.raw.aeroplane_audio),
            PlaylistTrack("Come to Me", 295, "Jul 1993",R.raw.cometome_audio),
            PlaylistTrack("Violently Happy", 298, "Jul 1993",R.raw.viotently_audio),
            PlaylistTrack("The Anchor Song", 212, "Jul 1993",R.raw.anchor_audio)
        )),

        AlbumInfo("malice_mizer", "Voyage ~Sans Retour~", "Malice Mizer", R.drawable.img_album_malice, listOf(
            PlaylistTrack("Bois de Merveilles", 115, "Jun 1996",R.raw.bois_audio),
            PlaylistTrack("Kioku to Sora", 285, "Jun 1996",R.raw.kioku_audio),
            PlaylistTrack("Syunikiss", 310, "Jun 1996",R.raw.syunikiss_audio),
            PlaylistTrack("Premier Amour", 225, "Jun 1996",R.raw.premier_audio),
            PlaylistTrack("Madrigal", 215, "Jun 1996",R.raw.madrigal_audio),
            PlaylistTrack("Bel Air", 256, "Jun 1996",R.raw.belair_audio),
            PlaylistTrack("Transilvania", 290, "Jun 1996",R.raw.transilvania_audio)
        )),

        AlbumInfo("theatres_vampires", "Moonlight Waltz", "Theatres des Vampires", R.drawable.img_album_theatres, listOf(
            PlaylistTrack("Keeper of Secrets", 255, "Feb 2011",R.raw.keeper_audio),
            PlaylistTrack("Carmilla", 299, "Feb 2011",R.raw.carmilla_audio),
            PlaylistTrack("Moonlight Waltz", 312, "Feb 2011",R.raw.moonligth_audio),
            PlaylistTrack("Fly Away", 260, "Feb 2011",R.raw.flyaway_audio),
            PlaylistTrack("Sangue", 235, "Feb 2011", R.raw.sangue_audio),
            PlaylistTrack("Figlio della Luna", 262, "Feb 2011",R.raw.figlio_audio),
            PlaylistTrack("Black Madonna", 280, "Feb 2011",R.raw.black_audio),
            PlaylistTrack("The Gates of Hades", 315, "Feb 2011",R.raw.thegates_audio),
            PlaylistTrack("Illusion", 290, "Feb 2011",R.raw.illusion_audio),
            PlaylistTrack("Le Grand Guignol", 245, "Feb 2011",R.raw.legrand_audio),
            PlaylistTrack("Obsession", 270, "Feb 2011",R.raw.obssesion_audio)
        )),

        AlbumInfo("akb48_koko", "Koko ni Ita Koto", "AKB48", R.drawable.img_album_akb48, listOf(
            PlaylistTrack("Shōjo-tachi yo", 270, "Jun 2011",R.raw.shojo_audio),
            PlaylistTrack("Ougon Center", 210, "Jun 2011",R.raw.ougon_audio),
            PlaylistTrack("Ponytail to Shushu", 269, "Jun 2011",R.raw.ponytail_audio),
            PlaylistTrack("Heavy Rotation", 280, "Jun 2011",R.raw.heavy_audio),
            PlaylistTrack("River", 283, "Jun 2011",R.raw.river_audio),
            PlaylistTrack("Beginner", 237, "Jun 2011",R.raw.beginner_audio),
            PlaylistTrack("Everyday, Katyusha", 312, "Jun 2011",R.raw.everyday_audio),
            PlaylistTrack("Kaze wa Fuiteiru", 215, "Jun 2011",R.raw.kaze_audio),
            PlaylistTrack("Flying Get", 255, "Jun 2011",R.raw.flyinget_audio),
            PlaylistTrack("Sakura no Ki ni Narou", 329, "Jun 2011",R.raw.sakura_audio),
            PlaylistTrack("Dareka no Tame ni", 253, "Jun 2011",R.raw.dareka_audio)
        )),

        AlbumInfo("plastique", "Dead Pop", "Plastique Noir", R.drawable.img_album_plastique, listOf(
            PlaylistTrack("Inconstancy", 210, "Nov 2008",R.raw.inconstancy_audio),
            PlaylistTrack("Phantom in My Stereo", 195, "Nov 2008",R.raw.phantom_audio),
            PlaylistTrack("Iml", 245, "Nov 2008",R.raw.iml_audio),
            PlaylistTrack("Imaginary Walls", 260, "Nov 2008",R.raw.imaginary_audio),
            PlaylistTrack("Creep Show", 280, "Nov 2008",R.raw.creep_audio),
            PlaylistTrack("Killdergarten", 190, "Nov 2008",R.raw.killdergarten_audio),
            PlaylistTrack("Desire or Disease", 235, "Nov 2008",R.raw.desire_audio),
            PlaylistTrack("Nihil", 255, "Nov 2008",R.raw.nihil_audio),
            PlaylistTrack("Shadowrun", 215, "Nov 2008",R.raw.shodowrun_audio),
            PlaylistTrack("Silent Shout", 275, "Nov 2008",R.raw.silent_audio)
        )),

        AlbumInfo("nanda_tsunami", "É disso que eu me alimento", "NandaTsunami", R.drawable.img_album_nanda, listOf(
            PlaylistTrack("Segredo e Feitiço", 195, "Oct 2025",R.raw.segredo_audio),
            PlaylistTrack("Pisca Duas Vezes", 210, "Oct 2025",R.raw.pisca_audio),
            PlaylistTrack("Meu Afeto É Obsceno", 188, "Oct 2025",R.raw.meuafeto_audio),
            PlaylistTrack("Solta", 175, "Oct 2026",R.raw.solta_audio),
            PlaylistTrack("Pq Vc Não Me Liga?", 205, "Oct 2025",R.raw.pqvcnao_audio),
            PlaylistTrack("Te Amei Infinito (Interlúdio)", 120, "Oct 2025",R.raw.teamei_audio),
            PlaylistTrack("Vai no Seu Progresso", 225, "Oct 2025",R.raw.vainoseu_audio),
            PlaylistTrack("Oi Linda", 190, "Oct 2025",R.raw.oilinda_audio),
            PlaylistTrack("Por Todo o Amor Que Eu Já Senti", 240, "Oct 2025",R.raw.portodo_audio)
        )),



        AlbumInfo("megan_stallion", "MEGAN", "Megan Thee Stallion", R.drawable.img_album_megan, listOf(
            PlaylistTrack("HISS", 192, "Jun 2024",R.raw.hiss_audio),
            PlaylistTrack("RATTLE", 210, "Jun 2024",R.raw.rattle_audio),
            PlaylistTrack("FIGUEROA", 185, "Jun 2024", R.raw.figueroa_audio),
            PlaylistTrack("WHERE THEM GIRLS AT", 220, "Jun 2024",R.raw.where_audio),
            PlaylistTrack("BROKE HIS HEART", 205, "Jun 2024",R.raw.broke_audio),
            PlaylistTrack("B.A.S. (feat. Kyle Richh)", 230, "Jun 2024",R.raw.bas_audio),
            PlaylistTrack("OTAKU HOT GIRL", 198, "Jun 2024",R.raw.otaku_audio),
            PlaylistTrack("FIND OUT", 215, "Jun 2024",R.raw.findout_audio),
            PlaylistTrack("BOA", 180, "Jun 2024",R.raw.boa_audio),
            PlaylistTrack("MAMUSHI (feat. Yuki Chiba)", 225, "Jun 2024",R.raw.mamushi_audio),
            PlaylistTrack("ACCENT (feat. GloRilla)", 212, "Jun 2024",R.raw.accent_audio),
            PlaylistTrack("PAPER TOGETHER (feat. UGK)", 240, "Jun 2024",R.raw.paper_audio),
            PlaylistTrack("SPIN (feat. Victoria Monét)", 218, "Jun 2024",R.raw.spin_audio),
            PlaylistTrack("DOWN STAIRS DJ", 202, "Jun 2024",R.raw.down_audio),
            PlaylistTrack("MIAMI BLUE", 210, "Jun 2024",R.raw.miami_audio),
            PlaylistTrack("WORTHY", 225, "Jun 2024",R.raw.worthy_audio),
            PlaylistTrack("MOODY GIRL", 195, "Jun 2024",R.raw.moody_audio),
            PlaylistTrack("COBRA", 228, "Jun 2024", R.raw.audio_cobra)
        )),

        AlbumInfo("cabaret_soundtrack", "Cabaret", "Various Artists", R.drawable.img_album_cabaret, listOf(
            PlaylistTrack("Willkommen", 185, "Feb 1972", R.raw.willkommen_audio),
            PlaylistTrack("Mein Herr", 215, "Feb 1972",R.raw.meinherr_audio),
            PlaylistTrack("Two Ladies", 190, "Feb 1972",R.raw.twoladies_audio),
            PlaylistTrack("Maybe This Time", 220, "Feb 1972",R.raw.maybethistime_audio),
            PlaylistTrack("Sitting Pretty", 165, "Feb 1972",R.raw.sitting_audio),
            PlaylistTrack("Tiller Girls", 175, "Feb 1972",R.raw.tiller_audio),
            PlaylistTrack("Money, Money", 210, "Feb 1972",R.raw.money_audio),
            PlaylistTrack("Heiraten", 205, "Feb 1972",R.raw.heiraten_audio),
            PlaylistTrack("If You Could See Her", 198, "Feb 1972",R.raw.ifyoucould_audio),
            PlaylistTrack("Tomorrow Belongs to Me", 180, "Feb 1972",R.raw.tomorrow_audio),
            PlaylistTrack("Cabaret", 230, "Feb 1972",R.raw.cabaret_audio),
            PlaylistTrack("Finale", 150, "Feb 1972",R.raw.finale_audio)
        )),

        AlbumInfo("ajulliacosta_novo", "Novo Testamento", "AJULLIACOSTA", R.drawable.img_album_ajullia, listOf(
            PlaylistTrack("Intro", 60, "Sep 2024",R.raw.intro_audio),
            PlaylistTrack("Toc Toc Toc", 180, "Sep 2024",R.raw.toctoctoc_audio),
            PlaylistTrack("Quero saber", 210, "Sep 2024",R.raw.querosaber_audio),
            PlaylistTrack("Dharma", 195, "Sep 2024",R.raw.dharma_audio),
            PlaylistTrack("O que a Julia vai ser?", 220, "Sep 2024",R.raw.oqueajulia_audio),
            PlaylistTrack("Liberdade", 205, "Sep 2024",R.raw.liberdade_audio),
            PlaylistTrack("Sob a luz do seu olhar", 215, "Sep 2024",R.raw.sobaluz_audio),
            PlaylistTrack("Acorde", 190, "Sep 2024",R.raw.acorde_audio),
            PlaylistTrack("Interlúdio [MINI SAIA]", 120, "Sep 2024",R.raw.interludio_audio),
            PlaylistTrack("Pense como uma diva", 230, "Sep 2024",R.raw.pensecomo_audio),
            PlaylistTrack("Fiel a mim", 212, "Sep 2024",R.raw.fielamim_audio)
        )),

        AlbumInfo("pink_fancy", "Fancy That", "PinkPantheress", R.drawable.img_banner_pinkpantheress, listOf(
            PlaylistTrack("Illegal", 155, "May 2025", R.raw.illegal_audio),
            PlaylistTrack("Girl Like Me", 180, "May 2025",R.raw.girlikeme_audio),
            PlaylistTrack("Tonight", 210, "May 2025",R.raw.tonight_audio),
            PlaylistTrack("Stars", 145, "May 2025",R.raw.stars_audio),
            PlaylistTrack("Intermission", 170, "May 2025",R.raw.intermission_audio),
            PlaylistTrack("Noises", 170, "May 2025",R.raw.noises_audio),
            PlaylistTrack("Nice to Know You", 195, "May 2025",R.raw.niceto_audio),
            PlaylistTrack("Stateside", 205, "May 2025",R.raw.stateside_audio),
            PlaylistTrack("Romeo", 212, "May 2025",R.raw.romeo_audio)
        )),

        AlbumInfo("vibin_mix", "vibin'", "usercore", R.drawable.img_album_vibin, listOf(
            PlaylistTrack("Si Antes Te Hubiera Conocido", 180, "2024", R.raw.siantes_audio),
            PlaylistTrack("(I Just) Died In Your Arms", 210, "1986",R.raw.ijust_audio),
            PlaylistTrack("Girls Just Want to Hve Fun", 195, "1983",R.raw.girlswant_audio),
            PlaylistTrack("Let's Dance", 185, "1983",R.raw.letsdance_audio),
            PlaylistTrack("All Mine", 220, "2022",R.raw.allmine_adio),
            PlaylistTrack("Is it a crime", 205, "2024",R.raw.crime_audio),
            PlaylistTrack("FANGS", 215, "2025",R.raw.fangs_audio),
            PlaylistTrack("Erva Venenosa", 190, "1985",R.raw.erva_audio),
        )),

        AlbumInfo("bedroom_mix", "bedroom mix", "usercore", R.drawable.img_album_bedroom, listOf(
            PlaylistTrack("Be Natural", 210, "2014",R.raw.benatural_audio),
            PlaylistTrack("Fade Into you", 245, "1993",R.raw.fade_audio),
            PlaylistTrack("Asleep Among Endives", 198, "2021",R.raw.asleep_audio),
            PlaylistTrack("Pink Leaves", 220, "2023",R.raw.pink_audio),
            PlaylistTrack("What More Can I Say", 215, "2024",R.raw.whatmore_audio),
            PlaylistTrack("In A Mist", 230, "1964",R.raw.mist_audio)
        )),

        AlbumInfo("cute_mix", "૮ ˶ᵔ ᵕ ᵔ˶ ა", "usercore", R.drawable.img_album_cute, listOf(
            PlaylistTrack("Aing", 180, "2012",R.raw.aing_audio),
            PlaylistTrack("NOT CUTE ANYMORE", 160, "2025",R.raw.notcute_audio),
            PlaylistTrack("Flyday Chinatown", 200, "2024",R.raw.flyday_audio),
            PlaylistTrack("Remember Summer Days", 175, "1983",R.raw.remember_audio),
            PlaylistTrack("Telecaster Stripes", 190, "2016",R.raw.telecaster_audio),
            PlaylistTrack("Harvey", 165, "2018",R.raw.harvey_audio),
            PlaylistTrack("Perfume", 210, "2006",R.raw.perfume_audio),
            PlaylistTrack("Im in the mood for dancing", 220, "2003",R.raw.iminthe_audio)
        )),

        AlbumInfo("favs_mix", "favs", "usercore", R.drawable.img_album_favs, listOf(
            PlaylistTrack("Queixa", 210, "1982",R.raw.queixa_audio),
            PlaylistTrack("Todavía me amas", 195, "2002",R.raw.todavia_audio),
            PlaylistTrack("True Romance", 230, "2023",R.raw.trueromance_audio),
            PlaylistTrack("Bonita", 180, "2025",R.raw.bonita_audio),
            PlaylistTrack("Le Ciel", 205, "1997",R.raw.leciel_audio),
            PlaylistTrack("NEMONEMO", 220, "2024",R.raw.nemonemo_audio)

        ))
    )

    fun getAlbum(id: String): AlbumInfo? = albums.find { it.id == id }
}