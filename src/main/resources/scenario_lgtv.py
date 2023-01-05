"""Basic webOS client example."""
import asyncio
from datetime import datetime
from contextlib import suppress
from aiowebostv import WebOsClient
from java.lang import System
from websockets.exceptions import ConnectionClosed, ConnectionClosedOK
from aiowebostv.exceptions import WebOsTvCommandError

HOST = "192.168.11.92"
# For first time pairing set key to None
CLIENT_KEY = "140cce792ae045920e14da4daa414582"

WEBOSTV_EXCEPTIONS = (
    OSError,
    ConnectionClosed,
    ConnectionClosedOK,
    ConnectionRefusedError,
    WebOsTvCommandError,
    asyncio.TimeoutError,
    asyncio.CancelledError,
)


class LG_TV:

    def __init__(self):
        self._loop = asyncio.new_event_loop()
        self.client = None
        self._listener = None

    async def create_lgtv(self):
        self.client = WebOsClient(HOST, CLIENT_KEY)
        await self.client.register_state_update_callback(self.on_state_change)


    async def on_state_change(self, client):
        """State changed callback."""
        self._listener.notifyUser(f"Volume: {client.volume}")
        self._listener.notifyUser(f"apps: {client.apps}")
        self._listener.notifyUser(f"Volume: {client.inputs}")
        #self._listener.notify({"volume": client.volume,
        #                       "System info": client.system_info,
        #                       "Software info": client.software_info,
        #                       "Hello info": client.hello_info,
        #                       "Channel info": client.channel_info,
        #                       "Apps": client.apps,
        #                       "Inputs": client.inputs,
        #                       "Volume": client.volume,
        #                       "Powered on": client.power_state,
        #                       "App Id": client.current_app_id,
        #                       "Channels": client.channels,
        #                       "Current channel": client.current_channel,
        #                       "Muted": client.muted,
        #                       "Sound output": client.sound_output,
        #                       "Is connected": client.is_connected(),
        #                       "Is on": client.is_on})
        #print(f"Sound output: {client.sound_output}")

    async def connect(self):
        """Webos client example."""
        # client = WebOsClient(HOST, CLIENT_KEY)
        # await client.register_state_update_callback(self.on_state_change)
        await self.client.connect()

        # Store this key for future use
        self._listener.notifyUser(f"Client key: {self.client.client_key}")

    async def reconnect(self):
        is_connected = False

        while not is_connected:
            await asyncio.sleep(1)
            is_connected = self.client.is_connected()
            if is_connected:
                break
            with suppress(*WEBOSTV_EXCEPTIONS):
                await self.client.connect()

    async def sleep(self):
        await asyncio.sleep(1)

