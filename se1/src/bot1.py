import requests
import time

TOKEN = "YOUR_BOT_TOKEN"

BASE_URL = f"https://tapi.bale.ai/bot{TOKEN}/"

offset = 0

while True:
    try:
        response = requests.get(
            BASE_URL + "getUpdates",
            params={"offset": offset, "timeout": 30}
        )

        data = response.json()

        if data.get("ok"):
            for update in data["result"]:

                offset = update["update_id"] + 1

                message = update.get("message")

                if message and "chat" in message:
                    chat_id = message["chat"]["id"]

                    requests.post(
                        BASE_URL + "sendMessage",
                        json={
                            "chat_id": chat_id,
                            "text": "خوبی؟"
                        }
                    )

    except Exception as e:
        print("Error:", e)
        time.sleep(3)