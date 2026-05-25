---
name: Dimension C-137 Interface
colors:
  surface: '#10141a'
  surface-dim: '#10141a'
  surface-bright: '#353941'
  surface-container-lowest: '#0a0e15'
  surface-container-low: '#181c22'
  surface-container: '#1c2027'
  surface-container-high: '#262a31'
  surface-container-highest: '#31353c'
  on-surface: '#dfe2ec'
  on-surface-variant: '#c3c9b3'
  inverse-surface: '#dfe2ec'
  inverse-on-surface: '#2d3138'
  outline: '#8d937f'
  outline-variant: '#434938'
  surface-tint: '#9fd754'
  primary: '#b2eb65'
  on-primary: '#203600'
  primary-container: '#97ce4c'
  on-primary-container: '#355500'
  inverse-primary: '#426900'
  secondary: '#ffaedc'
  on-secondary: '#531a41'
  secondary-container: '#74365d'
  on-secondary-container: '#f3a4d1'
  tertiary: '#ebdc45'
  on-tertiary: '#363100'
  tertiary-container: '#cec029'
  on-tertiary-container: '#544d00'
  error: '#ffb4ab'
  on-error: '#690005'
  error-container: '#93000a'
  on-error-container: '#ffdad6'
  primary-fixed: '#baf46d'
  primary-fixed-dim: '#9fd754'
  on-primary-fixed: '#112000'
  on-primary-fixed-variant: '#314f00'
  secondary-fixed: '#ffd8eb'
  secondary-fixed-dim: '#ffaedc'
  on-secondary-fixed: '#39032b'
  on-secondary-fixed-variant: '#6e3158'
  tertiary-fixed: '#f5e64e'
  tertiary-fixed-dim: '#d8c933'
  on-tertiary-fixed: '#1f1c00'
  on-tertiary-fixed-variant: '#4e4800'
  background: '#10141a'
  on-background: '#dfe2ec'
  surface-variant: '#31353c'
  portal-green: '#97ce4c'
  toxic-purple: '#e89ac7'
  smith-yellow: '#f0e14a'
  deep-space-bg: '#24282f'
  surface-card: '#3c3e44'
  status-alive: '#55cc44'
  status-dead: '#d63d2e'
  status-unknown: '#9e9e9e'
typography:
  headline-xl:
    fontFamily: Sora
    fontSize: 40px
    fontWeight: '800'
    lineHeight: 48px
    letterSpacing: -0.02em
  headline-lg:
    fontFamily: Sora
    fontSize: 32px
    fontWeight: '700'
    lineHeight: 40px
    letterSpacing: -0.01em
  headline-md:
    fontFamily: Sora
    fontSize: 24px
    fontWeight: '600'
    lineHeight: 32px
  body-lg:
    fontFamily: JetBrains Mono
    fontSize: 18px
    fontWeight: '400'
    lineHeight: 28px
  body-md:
    fontFamily: JetBrains Mono
    fontSize: 16px
    fontWeight: '400'
    lineHeight: 24px
  body-sm:
    fontFamily: JetBrains Mono
    fontSize: 14px
    fontWeight: '400'
    lineHeight: 20px
  label-caps:
    fontFamily: JetBrains Mono
    fontSize: 12px
    fontWeight: '700'
    lineHeight: 16px
    letterSpacing: 0.1em
rounded:
  sm: 0.125rem
  DEFAULT: 0.25rem
  md: 0.375rem
  lg: 0.5rem
  xl: 0.75rem
  full: 9999px
spacing:
  base: 8px
  container-margin: 20px
  gutter: 12px
  stack-sm: 4px
  stack-md: 16px
  stack-lg: 32px
---

## Brand & Style

The design system is a high-octane, sci-fi interface designed for the intergalactic traveler. It balances the chaotic energy of the show with the rigorous precision of a Galactic Federation terminal. The style is **Futuristic/Skeuomorphic**, characterized by "toxic" neon glows, deep space depth, and technical flourishes. 

The aesthetic leverages a dark-mode foundation to make "Portal Green" and "Toxic Purple" accents pop with radioactive intensity. It targets a tech-savvy audience that appreciates high-density information layouts, animated transitions that mimic portal travel, and a "built-in-a-garage" yet highly advanced engineering vibe.

## Colors

The palette is anchored in **Deep Space Navy** for backgrounds to maximize the luminance of the primary brand colors. 

- **Primary (Portal Green):** Used for interactive elements, successful states, and "active" energy signals. 
- **Secondary (Toxic Purple):** Used for secondary interactions, rare data types, and experimental UI flourishes.
- **Tertiary (Smith Yellow):** Reserved for warnings, critical highlights, and specific character-related data points.
- **Neutral:** A range of desaturated grays (#3c3e44 and #9e9e9e) are used for structural containment and secondary text to prevent visual fatigue.

All interactive elements should utilize a subtle outer glow (0px 0px 8px) in their respective brand color to simulate a radioactive emission.

## Typography

This design system utilizes a dual-font strategy to balance character and utility:

1.  **Sora:** Chosen for its geometric, futuristic, and bold presence. It handles all display and headline roles, mirroring the high-energy impact of the show's title cards.
2.  **JetBrains Mono:** A technical, monospaced font used for all body text, labels, and data points. This evokes a "terminal" or "ship computer" aesthetic, ensuring that even dense character stats remain legible and feel like part of a scientific readout.

Headlines should occasionally use an "italic" tilt or a slight glow effect to emphasize chaotic moments in the UI.

## Layout & Spacing

The layout is optimized for a **Mobile-First** experience using a 4-column fluid grid. 

- **Margins:** A standard 20px outer margin ensures content doesn't bleed into the physical edges of modern mobile displays.
- **Rhythm:** Spacing follows an 8px base unit. 
- **Structure:** Content is organized into "Modules" or "Data Blocks." Because the theme is slightly chaotic, elements can occasionally break the grid with "glitch" offsets (e.g., a card header shifted 4px to the left) to add visual interest without sacrificing usability.
- **Safe Areas:** Strict adherence to bottom-screen safe areas is required for navigation elements, styled as floating "Control Orbs" or "Portal Tabs."

## Elevation & Depth

Hierarchy is established through **Tonal Layering** and **Luminescent Depth** rather than traditional shadows:

- **Level 0 (Floor):** Deep Space Navy (#24282f).
- **Level 1 (Cards/Containers):** Surface-card (#3c3e44) with a subtle 1px inner border in a lighter gray to define edges.
- **Level 2 (Interactive/Floating):** Use of "Portal Green" or "Toxic Purple" neon outer glows (8px-16px blur) to lift elements off the surface.
- **Glassmorphism:** Navigation bars and modal overlays should use a background blur (12px) with a semi-transparent hex of the Neutral color (alpha 0.7) to simulate a reinforced glass visor.
- **Borders:** All containers should feature "clipped corners" or "technical notches" to reinforce the futuristic hardware aesthetic.

## Shapes

The shape language is **Angular & Technical**. 

While the base roundedness is set to `1` (Soft, 0.25rem), this is often overridden by custom "Clipped Corner" shapes. Use 45-degree chamfered edges on buttons and card headers. This creates a more aggressive, sci-fi feel than standard rounding. 

Portal-related elements (like profile pictures or portal transitions) are the only exception and should remain perfectly circular to mimic the shape of the portal gun's output.

## Components

### Buttons
Primary buttons use a solid **Portal Green** fill with black text (Sora, Bold). They feature a "Neon Flicker" animation on press. Secondary buttons use a **Toxic Purple** outline with a 1px border and a subtle glow.

### Cards
Character cards are the primary vessel of information. They should feature:
- A large, circular character image that breaks the top-left border.
- A "Status Indicator" dot (Alive/Dead/Unknown) with a pulsing animation.
- A background of #3c3e44 with a 1px border of #9E9E9E at 30% opacity.

### Input Fields
Inputs are styled as "Data Entry Terminals." Use a dark fill, a bottom-only border in Portal Green, and a monospaced cursor. Labels should use `label-caps`.

### Portal Transitions
When navigating between views, use a radial expansion effect starting from the center of the screen, utilizing a swirling green gradient to simulate stepping through a portal.

### Chips/Tags
Small, rectangular containers with no rounding and a chamfered top-right corner. Used for "Species" or "Origin" labels, utilizing JetBrains Mono for a data-heavy look.