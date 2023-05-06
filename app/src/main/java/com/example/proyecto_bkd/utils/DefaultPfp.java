package com.example.proyecto_bkd.utils;

public class DefaultPfp {
    public static final String[] DEFAULT_URLS =
            {
                    "https://firebasestorage.googleapis.com/v0/b/bunny-kingdom-deluxe.appspot.com/o/pfp%2Fconejo1.jpg?alt=media&token=e82a9838-d11e-4760-ae7e-a97f36618d7b",
                    "https://firebasestorage.googleapis.com/v0/b/bunny-kingdom-deluxe.appspot.com/o/pfp%2Fconejo2.jpg?alt=media&token=9f322c3e-5b1d-49da-bf4a-8ecd341b7cf8",
                    "https://firebasestorage.googleapis.com/v0/b/bunny-kingdom-deluxe.appspot.com/o/pfp%2Fconejo3.jpg?alt=media&token=0bc3decd-dc63-4aec-8a89-501d355a85df",
                    "https://firebasestorage.googleapis.com/v0/b/bunny-kingdom-deluxe.appspot.com/o/pfp%2Fconejo4.jpg?alt=media&token=e893bcfc-a9a2-4b81-bf55-0954c75f9cb7",
                    "https://firebasestorage.googleapis.com/v0/b/bunny-kingdom-deluxe.appspot.com/o/pfp%2Fconejo5.jpg?alt=media&token=905760b1-abde-483f-9301-cb0260f3ec24",
                    "https://firebasestorage.googleapis.com/v0/b/bunny-kingdom-deluxe.appspot.com/o/pfp%2Fconejo6.jpg?alt=media&token=6c6477f2-4963-4513-98dd-269fd680a313",
                    "https://firebasestorage.googleapis.com/v0/b/bunny-kingdom-deluxe.appspot.com/o/pfp%2Fconejo7.jpg?alt=media&token=e450b390-a03e-47b8-859b-4a37425dcf6f",
                    "https://firebasestorage.googleapis.com/v0/b/bunny-kingdom-deluxe.appspot.com/o/pfp%2Fconejo8.jpg?alt=media&token=7e0fea28-10fa-468a-bead-5f4f720a2204",
                    "https://firebasestorage.googleapis.com/v0/b/bunny-kingdom-deluxe.appspot.com/o/pfp%2Fconejo9.jpg?alt=media&token=64542eac-e404-42f9-9067-a62d90d88686",
                    "https://firebasestorage.googleapis.com/v0/b/bunny-kingdom-deluxe.appspot.com/o/pfp%2Fconejo10.jpg?alt=media&token=959f76a4-a92a-45a8-8b11-334997a81a47",
                    "https://firebasestorage.googleapis.com/v0/b/bunny-kingdom-deluxe.appspot.com/o/pfp%2Fconejo11.jpg?alt=media&token=35c6c95d-b4eb-49dc-a9a8-3b1d6ec759ff"
            };
    public static String getRandomDefaultPfp() {
        int randomN = (int) (Math.random() * DEFAULT_URLS.length);
        return DEFAULT_URLS[randomN];
    }
}
