# Objects

### ObfuscatedCells

```json
{
  "x": int,
  "y": int,
  "value": int // 0-8 values, -1 flag,  -2 hidden
}
```

---

# Http endpoints

#### POST /create

Response

`uuid-game` It's a UUID game
302 /play?game=uuid-game

### ws://host:port/ws

```json
{
  "gameId": uuid-game
}
```

---

# WebSockets data packages

### Server -> Client "sync" ALL

```json
{
  "timestamp": long, // Millisecond
  "type": "ALL", // ALL, PARTIAL
  "cells": ObfuscatedCells[][]
}
```

### Server -> Client "win" ALL
```json
{
  "timestamp": long,
  "cells": ObfuscatedCells[][],
  "flags": [
    {
        "x": int,
        "y": int
    }
  ]
}
```

```json
{
  "timestamp": long, // Millisecond
  "type": "PARTIAL", // PARTIAL
  "cells": ObfuscatedCells[]
}
```
### Client -> Server "flag"

```json
{
  "timestamp": long, // Millisecond
  "position": {
    "x": int,
    "y": int
  }
}
```

### Client -> Server "discover"

```json
{
  "timestamp": long, // Millisecond
  "position": {
    "x": int,
    "y": int
  }
}
```