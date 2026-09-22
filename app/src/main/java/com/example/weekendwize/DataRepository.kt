package com.example.weekendwize

object DataRepository {

    val subjects = listOf(
        // ============================
        // MATHEMATICS
        // ============================
        Subject(
            "Mathematics",
            "Grade 11",
            listOf(
                Topic(
                    "Algebraic Expressions",
                    listOf(
                        SubTopic(
                            "Simplifying Expressions",
                            Quiz(listOf(
                                Question("What is 2x + 3x?", listOf("4x", "5x", "6x", "7x"), 1),
                                Question("Solve for x: x - 5 = 0", listOf("0", "5", "-5", "10"), 1)
                            )),
                            theory = "Simplifying expressions involves combining like terms. Like terms have the same variables raised to the same powers.",
                            notes = "Always group terms with the same variable before adding or subtracting their coefficients."
                        ),
                        SubTopic(
                            "Factoring",
                            Quiz(listOf(
                                Question("Factor x² - 4", listOf("(x - 2)(x + 2)", "(x - 2)(x - 2)", "(x + 2)(x + 2)", "x(x - 4)"), 0)
                            )),
                            theory = "Factoring is the process of breaking down a polynomial into a product of simpler polynomials.",
                            notes = "Difference of squares formula: a² - b² = (a - b)(a + b)."
                        )
                    )
                ),
                Topic("Functions & Graphs", emptyList())
            ),
            theory = """
                MATHEMATICS THEORY - GRADE 11
                
                1. ALGEBRAIC EXPRESSIONS:
                - Exponents: Understanding laws of exponents (a^m * a^n = a^(m+n)).
                - Surds: Simplifying roots and rationalizing denominators.
                - Equations: Solving quadratic equations using factoring, completing the square, or the quadratic formula: x = [-b ± √(b² - 4ac)] / 2a.
                
                2. FUNCTIONS:
                - Linear (y = mx + c): Understanding gradient and intercepts.
                - Quadratic (y = a(x-p)² + q): Finding the turning point and axis of symmetry.
                - Hyperbolic (y = a/(x-p) + q): Identifying vertical and horizontal asymptotes.
                - Exponential (y = a·b^(x-p) + q): Understanding growth and decay.
                
                3. TRIGONOMETRY:
                - Definitions: sinθ = opp/hyp, cosθ = adj/hyp, tanθ = opp/adj.
                - Identities: sin²θ + cos²θ = 1; tanθ = sinθ/cosθ.
                - Reduction Formulae: Using CAST diagram to simplify ratios in different quadrants.
                - Sine, Cosine, and Area Rules for non-right-angled triangles.
            """.trimIndent(),
            notes = """
                MATHEMATICS QUICK REVISION NOTES
                
                - Quadratic Formula Tip: If b² - 4ac < 0, the roots are non-real (imaginary).
                - Functions: The 'q' value in y = f(x) + q always shifts the graph vertically.
                - Trigonometry: Remember that sin is positive in the 1st and 2nd quadrants, while cos is positive in the 1st and 4th.
                - Analytical Geometry: The product of gradients of perpendicular lines is always -1 (m1 * m2 = -1).
                - Number Patterns: For linear patterns, use Tn = dn + (a - d). For quadratic patterns, the second difference is constant.
            """.trimIndent(),
            revision = """
                MATHEMATICS EXAM PREPARATION GUIDE
                
                PAPER 1 FOCUS:
                - Algebra, Equations & Inequalities (±25 marks)
                - Number Patterns (±25 marks)
                - Functions & Graphs (±35 marks)
                - Finance, Growth & Decay (±15 marks)
                - Probability (±20 marks)
                
                PAPER 2 FOCUS:
                - Statistics (±20 marks)
                - Analytical Geometry (±30 marks)
                - Trigonometry (±50 marks)
                - Euclidean Geometry (±50 marks)
                
                Top Tip: Past papers are your best friend. Practice at least 3 full papers before the exam!
            """.trimIndent()
        ),

        // ============================
        // PHYSICAL SCIENCES
        // ============================
        Subject(
            "Physical Sciences",
            "Grade 11",
            listOf(
                Topic("Newton's Laws", emptyList()),
                Topic("Chemical Bonding", emptyList())
            ),
            theory = """
                PHYSICAL SCIENCES THEORY - GRADE 11
                
                1. PHYSICS (MECHANICS):
                - Newton's First Law: An object continues in a state of rest or uniform motion unless acted upon by a net force (Inertia).
                - Newton's Second Law: When a net force is applied to an object, it accelerates in the direction of the force (Fnet = ma).
                - Newton's Third Law: When object A exerts a force on object B, object B exerts an equal and opposite force on object A.
                - Newton's Law of Universal Gravitation: F = G(m1·m2)/r².
                
                2. CHEMISTRY (MATTER & MATERIALS):
                - Atomic Combinations: Atoms bond to become more stable (Octet rule).
                - Molecular Structure: Using VSEPR theory to determine molecular shapes (Linear, Bent, Trigonal Planar, etc.).
                - Intermolecular Forces: Understanding London forces, Dipole-dipole forces, and Hydrogen bonding.
                - Ideal Gases: Boyle's Law, Charles's Law, and the Ideal Gas Equation (PV = nRT).
            """.trimIndent(),
            notes = """
                PHYSICAL SCIENCES KEY POINTS
                
                - Forces: Always draw a Free Body Diagram (FBD) before starting a mechanics problem.
                - Momentum: Remember that linear momentum (p = mv) is conserved in isolated systems.
                - Work & Energy: Work done is W = FΔx cosθ. Energy cannot be created or destroyed, only transformed.
                - Chemical Bonding: Electronegativity difference determines if a bond is non-polar covalent (<0.5), polar covalent (0.5-1.7), or ionic (>1.7).
                - Stoichiometry: Always balance your chemical equation first! Use n = m/M for mass and n = V/Vm for gases at STP.
            """.trimIndent(),
            revision = """
                PHYSICAL SCIENCES STUDY STRATEGY
                
                PHYSICS FOCUS AREAS:
                - Vectors in 2D
                - Newton's Laws and Applications
                - Electrostatics (Coulomb's Law)
                - Electromagnetism (Faraday's Law)
                
                CHEMISTRY FOCUS AREAS:
                - Chemical Bonding & Intermolecular Forces
                - Quantitative Aspects of Chemical Change
                - Lithosphere (Exploiting resources)
                
                Exam Advice: Show all your work. Even if the final answer is wrong, you can get marks for the formula and substitution.
            """.trimIndent()
        ),

        // ============================
        // LIFE SCIENCES
        // ============================
        Subject(
            "Life Sciences",
            "Grade 11",
            listOf(
                Topic("Photosynthesis", emptyList()),
                Topic("Animal Nutrition", emptyList())
            ),
            theory = """
                LIFE SCIENCES THEORY - GRADE 11
                
                1. PHOTOSYNTHESIS:
                - The process by which plants make glucose using sunlight, CO2, and water.
                - Light-dependent phase: Occurs in the thylakoids/grana; produces O2, ATP, and NADPH.
                - Light-independent phase (Calvin Cycle): Occurs in the stroma; uses CO2 to produce glucose.
                
                2. ANIMAL NUTRITION:
                - Digestion: The breakdown of large insoluble food molecules into small water-soluble ones.
                - Processes: Ingestion, Digestion, Absorption, Assimilation, and Egestion.
                - Human Digestive System: Functions of the mouth, stomach, small intestine (villi), and liver (bile).
                
                3. GASEOUS EXCHANGE:
                - Requirements for efficient exchange: Large surface area, thin membrane, moist, and well-ventilated.
                - Human Respiratory System: Structure of the lungs and the mechanism of ventilation (diaphragm and intercostal muscles).
            """.trimIndent(),
            notes = """
                LIFE SCIENCES STUDY TIPS
                
                - Diagrams: You must be able to draw and label the Chloroplast, Mitochondrion, and the Human Digestive System.
                - Photosynthesis: Remember that CO2 is the limiting factor in the dark phase, while light intensity affects the light phase.
                - Respiration: Do not confuse Cellular Respiration (making ATP) with Breathing (ventilation).
                - Biodiversity: Focus on the characteristics of the four plant groups: Bryophytes, Pteridophytes, Gymnosperms, and Angiosperms.
                - Excretion: The Nephron is the functional unit of the kidney. Understand filtration, reabsorption, and tubular excretion.
            """.trimIndent(),
            revision = """
                LIFE SCIENCES REVISION CHECKLIST
                
                TERM 1:
                - Biodiversity of Micro-organisms
                - Biodiversity of Plants and Animals
                
                TERM 2:
                - Photosynthesis
                - Animal Nutrition
                
                TERM 3:
                - Gaseous Exchange
                - Excretion in Humans
                - Population Ecology
                
                Active Learning: Use flashcards for biological terms and definitions. They account for a significant portion of Paper 1 and 2.
            """.trimIndent()
        ),

        // ============================
        // ACCOUNTING
        // ============================
        Subject(
            "Accounting",
            "Grade 11",
            listOf(
                Topic("Partnerships", emptyList()),
                Topic("Cost Accounting", emptyList())
            ),
            theory = """
                ACCOUNTING THEORY - GRADE 11
                
                1. PARTNERSHIPS:
                - Formation: 2 to 20 partners (usually).
                - Legal Entity: A partnership is not a separate legal entity from the owners.
                - Financial Statements: Income Statement, Appropriation Account, and Balance Sheet (with Capital and Current accounts for each partner).
                
                2. COST ACCOUNTING (MANUFACTURING):
                - Direct Costs: Raw materials and Direct labor.
                - Indirect Costs: Factory overheads (rent, electricity for factory).
                - Total Production Cost = Direct Materials + Direct Labor + Factory Overheads.
                
                3. BUDGETING:
                - Cash Budget: Predicting future cash inflows and outflows.
                - Projected Income Statement: Predicting future profit or loss.
                
                4. RECONCILIATIONS:
                - Bank Reconciliation: Matching the business's Bank account with the Bank Statement.
            """.trimIndent(),
            notes = """
                ACCOUNTING KEY CONCEPTS
                
                - The Accounting Equation: A = L + OE.
                - Double Entry: Every transaction affects at least two accounts. Debits on the left, Credits on the right.
                - Partners' Equity: Current accounts record the partners' share of profits, drawings, and interest on capital.
                - Inventory Systems: Perpetual system (cost of sales updated per sale) vs. Periodic system (cost of sales calculated at year-end).
                - Ethics: Integrity, Objectivity, and Professional Competence are vital for accountants.
            """.trimIndent(),
            revision = """
                ACCOUNTING EXAM TIPS
                
                PAPER 1:
                - Financial Statements of Partnerships
                - Analysis and Interpretation of Financial Statements
                - Ethics and Internal Control
                
                PAPER 2:
                - Cost Accounting (Manufacturing)
                - Budgeting
                - Reconciliation (Bank and Creditors)
                - Value Added Tax (VAT)
                
                Practice Strategy: Work on your speed. Accounting exams are often long, so practice completing ledgers and statements within the time limits.
            """.trimIndent()
        )
    )
}
